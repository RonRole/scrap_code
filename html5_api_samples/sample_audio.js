window.AudioContext = window.AudioContext || window.webkitAudioContext;

class Audio {

    #ctx = new AudioContext();

    #gainNode = this.#ctx.createGain();

    #oscillator = null;
    #isPlaying = false;

    constructor() {
        this.#gainNode.gain.value = 0.5;
    }

    setGainValue(value) {
        this.#gainNode.gain.value = value;
    }

    play(type, frequency) {
        if(this.#isPlaying) {
            this.stop();
        }
        // 音源の生成
        // 選択中のplay/volume/frequencyから作成する
        this.#oscillator = this.#ctx.createOscillator();
        this.#oscillator.type = type;
        this.#oscillator.frequency.value = frequency;
        this.#oscillator.connect(this.#gainNode);
        this.#gainNode.connect(this.#ctx.destination);

        // 再生
        this.#oscillator.start();
        this.#isPlaying = true;
    }

    stop() {
        if(!this.#oscillator) {
            return;
        }
        this.#oscillator.stop();
        this.#oscillator = null;
        this.#isPlaying = false;
    }
}

const properties = {
    type: "sine",
    frequency: "880",
}

const audio = new Audio();

document.getElementById("play").addEventListener("click", () => {
    audio.play(properties.type, properties.frequency);
});

document.getElementById("stop").addEventListener("click", () => {
    audio.stop();
});

document.getElementById("volumes").addEventListener("change", e => {
    audio.setGainValue(e.currentTarget.value);
});

document.getElementById("type").addEventListener("change", e => {
    properties.type = e.currentTarget.value;
    audio.play(properties.type, properties.frequency);
});

document.getElementById("frequency").addEventListener("change", e => {
    properties.frequency = e.currentTarget.value;
    audio.play(properties.type, properties.frequency);
})