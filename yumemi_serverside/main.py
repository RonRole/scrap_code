import csv
import decimal

# プレイログを読み取る
def load_playlog(filename):
    try:
        with open(filename, 'r') as f:
            reader = csv.reader(f)
            # ヘッダをスキップする
            next(reader)
            return [[row[1],int(row[2])] for row in reader]
    except:
        print("プレイログを読み取れませんでした")
        return []


# プレイヤーID→(得点合計、件数)のハッシュにする
def make_player_score_dict(playlog):
    res = dict()
    for row in playlog:
        player_id, score = row[0], row[1]

        if not res.get(player_id):
            res[player_id] = (score, 1)
        else:
            current_score, current_count = res[player_id][0], res[player_id][1]
            res[player_id] = (current_score + score, current_count + 1)
    return res

# player_score_dict → (プレイヤーID, 平均得点)のハッシュにする
# 小数点第一位を四捨五入して正数にする
def make_player_avg(player_score_dict):
    res = dict()
    for k,v in player_score_dict.items():
        avg = v[0]/v[1]
        # 「整数値」に「四捨五入」する
        rounded_decimal = decimal.Decimal(str(avg)).quantize(decimal.Decimal('0'), rounding='ROUND_HALF_UP')
        res[k] = int(rounded_decimal)
    return res

# ソートする
## ・同じ平均点の場合は同じランク
## ・同じランクが複数ある場合、次からはスキップする 
def sort_player_score(player_avg):
    return sorted(player_avg.items(), key=lambda x: x[1], reverse=True)

def print_ranking(sorted_player_score):
    print(sorted_player_score)

if __name__ == "__main__":
    playlog = load_playlog("playlog.csv")
    player_score = make_player_score_dict(playlog)
    player_avg = make_player_avg(player_score)
    result = sort_player_score(player_avg)
    print_ranking(result)