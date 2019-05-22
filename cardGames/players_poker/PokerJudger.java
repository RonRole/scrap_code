package players_poker;

import java.util.*;
import java.util.stream.*;

import card.*;
import interfaces.*;
import players_general.*;

/*
 * Judgerは、手札を見せれば役の判定をしてくれます。
 * もちろんシングルトンです
 */
class PokerJudger extends Judger {

	public static JudgerInterface getJudger() {
		if(judger == null) {
			judger = new PokerJudger();
		}
		return judger;
	}

	//テスト用
	public static void main(String[] args) {
		List<Card> testHand = new ArrayList<>();
		testHand.add(new Card(-1,-1));
		testHand.add(new Card(3,4));
		testHand.add(new Card(1,9));
		testHand.add(new Card(3,9));
		testHand.add(new Card(3,1));

		String s1 = testHand.toString();
		String s2 = ((PokerJudgeResult) PokerJudger.getJudger().judge(testHand)).getResult();
		System.out.println(s1 + s2);

	}

	@Override
	public JudgeResult judge(List<Card> hand) {
		// TODO 自動生成されたメソッド・スタブ
		//手札のAの数値を14とする
		hand.stream().filter(i -> i.getNumber() == Card.ACE).forEach(i -> i.setTempNumber(14));
		return judgeHand(hand);
	}

	@Override
	public List<PlayerInterface> getRankedPlayers(List<PlayerInterface> players) {
		//手札のAの数値を14とする
		players.stream().forEach(player -> {
			player.giveHand().stream().filter(card -> card.getNumber() == Card.ACE).forEach(card -> card.setTempNumber(14));
		});
		// TODO 自動生成されたメソッド・スタブ
		List<PlayerInterface> rankedPlayers = players.stream().sorted(Comparator.comparing(i -> judgeHand(((Player) i).giveHand()).getHandNum())
													.thenComparing(i -> judgeHand(((Player) i).giveHand()).getGroupedCards().stream().mapToInt(card -> card.getNumber()).sum())
													.thenComparing(i -> judgeHand(((Player) i).giveHand()).getGroupedCards().stream().mapToInt(card -> card.getMark()).sum()))
													.collect(Collectors.toList());
		//手札のAの数値を元に戻す
		players.stream().forEach(player -> {
			player.giveHand().stream().filter(card -> card.getNumber() == 14).forEach(card -> card.setTempNumber(Card.ACE));
		});

		return rankedPlayers;

	}


	/*
	 * 番号と役の対応です
	 */
	private static int handNum = 0;
	public final static int NO_PAIR = handNum;
	public final static int ONE_PAIR = ++handNum;
	public final static int TWO_PAIR = ++handNum;
	public final static int THREE_OF_A_KIND= ++handNum;
	public final static int STRAIGHT = ++handNum;
	public final static int FLUSH = ++handNum;
	public final static int FULL_HOUSE = ++handNum;
	public final static int FOUR_OF_A_KIND = ++handNum;
	public final static int STRAIGHT_FLUSH = ++handNum;
	public final static int ROYAL_STRAIGHT_FLUSH = ++handNum;

	/*
	 * 役名とのマッピング
	 */
	static final Map<Integer, String> pokerHandMap = new HashMap<Integer, String>(){
		{
			put(NO_PAIR, "NoPair");
			put(ONE_PAIR, "OnePair");
			put(TWO_PAIR, "TwoPair");
			put(THREE_OF_A_KIND, "ThreeOfAKind");
			put(STRAIGHT, "Straight");
			put(FLUSH, "Flush");
			put(FULL_HOUSE, "FullHouse");
			put(FOUR_OF_A_KIND, "FourOfAKind");
			put(STRAIGHT_FLUSH, "StraightFlush");
			put(ROYAL_STRAIGHT_FLUSH,"RoyalStraightFlush");
		}
	};

	//ジョーカーの有無をチェックします。
	private boolean checkJoker(List<Card> hand) {
		return hand.stream().anyMatch(card -> card.getMark() == Card.JOKER_MARK && card.getNumber() == Card.JOKER_NUMBER);
	}

	//与えられた手札を判断します
	PokerJudgeResult judgeHand(List<Card> hand) {
		int resultHand = NO_PAIR;//0の時はノーペアです

		Optional<Card> tempResultNumber = hand.stream().max(Comparator.comparing(i -> i.getNumber()));
		int resultNumber = tempResultNumber.get().getNumber();

		Optional<Card> tempResultMark = hand.stream().max(Comparator.comparing(i -> i.getMark()));
		int resultMark = tempResultMark.get().getMark();

		Map<Object,List<Card>> numberMap = makeHandGroupByNumber(hand);

		List<Card> groupedCard = numberMap.get(resultNumber);

		//役判定
		if(checkJoker(hand)) {
			if(isOnePairCaseJoker(hand)) {
				resultHand = ONE_PAIR;

				List<Card> tempCardList = new ArrayList<>();
				numberMap.values().stream().filter(i -> i.size() > 1).forEach(i -> tempCardList.addAll(i));
				groupedCard.clear();
				groupedCard.addAll(tempCardList);
			}
			if(isTwoPair(hand)) {
				resultHand = TWO_PAIR;

				List<Card> tempCardList = new ArrayList<>();
				numberMap.values().stream().filter(i -> i.size() > 1).forEach(i -> tempCardList.addAll(i));
				groupedCard.clear();
				groupedCard.addAll(tempCardList);
			}
			if(isThreeOfAKindCaseJoker(hand)) {
				resultHand = THREE_OF_A_KIND;

				List<Card> tempCardList = new ArrayList<>();
				numberMap.values().stream().filter(i -> i.size() > 1).forEach(i -> tempCardList.addAll(i));
				groupedCard.clear();
				groupedCard.addAll(tempCardList);
			}
			if(isStraightCaseJoker(hand)) {
				resultHand = STRAIGHT;

				groupedCard.clear();
				groupedCard.addAll(hand);
			}
			if(isFlushCaseJoker(hand)) {
				resultHand = FLUSH;

				groupedCard.clear();
				groupedCard.addAll(hand);
			}
			if(isFullHouseCaseJoker(hand)) {
				resultHand = FULL_HOUSE;

				List<Card> tempCardList = new ArrayList<>();
				numberMap.values().stream().filter(i -> i.size() > 1).forEach(i -> tempCardList.addAll(i));
				groupedCard.clear();
				groupedCard.addAll(tempCardList);

			}
			if(isFourOfAKindCaseJoker(hand)) {
				resultHand = FOUR_OF_A_KIND;

				List<Card> tempCardList = new ArrayList<>();
				numberMap.values().stream().filter(i -> i.size() > 1).forEach(i -> tempCardList.addAll(i));
				groupedCard.clear();
				groupedCard.addAll(tempCardList);
			}
			if(isStraightFlushCaseJoker(hand)) {
				resultHand = STRAIGHT_FLUSH;

				groupedCard.clear();
				groupedCard.addAll(hand);
			}
			if(isRoyalStraightFlushCaseJoker(hand)) {
				resultHand = ROYAL_STRAIGHT_FLUSH;

				groupedCard.clear();
				groupedCard.addAll(hand);
			}
		}
		else {
			//役判定
			if(isOnePair(hand)) {
				resultHand = ONE_PAIR;

				List<Card> tempCardList = new ArrayList<>();
				numberMap.values().stream().filter(i -> i.size() > 1).forEach(i -> tempCardList.addAll(i));
				groupedCard.clear();
				groupedCard.addAll(tempCardList);
			}
			if(isTwoPair(hand)) {
				resultHand = TWO_PAIR;

				List<Card> tempCardList = new ArrayList<>();
				numberMap.values().stream().filter(i -> i.size() > 1).forEach(i -> tempCardList.addAll(i));
				groupedCard.clear();
				groupedCard.addAll(tempCardList);
			}
			if(isThreeOfAKind(hand)) {
				resultHand = THREE_OF_A_KIND;

				List<Card> tempCardList = new ArrayList<>();
				numberMap.values().stream().filter(i -> i.size() > 1).forEach(i -> tempCardList.addAll(i));
				groupedCard.clear();
				groupedCard.addAll(tempCardList);
			}
			if(isStraight(hand)) {
				resultHand = STRAIGHT;

				groupedCard.clear();
				groupedCard.addAll(hand);
			}
			if(isFlush(hand)) {
				resultHand = FLUSH;

				groupedCard.clear();
				groupedCard.addAll(hand);
			}
			if(isFullHouse(hand)) {
				resultHand = FULL_HOUSE;

				List<Card> tempCardList = new ArrayList<>();
				numberMap.values().stream().filter(i -> i.size() > 1).forEach(i -> tempCardList.addAll(i));
				groupedCard.clear();
				groupedCard.addAll(tempCardList);

			}
			if(isFourOfAKind(hand)) {
				resultHand = FOUR_OF_A_KIND;

				List<Card> tempCardList = new ArrayList<>();
				numberMap.values().stream().filter(i -> i.size() > 1).forEach(i -> tempCardList.addAll(i));
				groupedCard.clear();
				groupedCard.addAll(tempCardList);
			}
			if(isStraightFlush(hand)) {
				resultHand = STRAIGHT_FLUSH;

				groupedCard.clear();
				groupedCard.addAll(hand);
			}
			if(isRoyalStraightFlush(hand)) {
				resultHand = ROYAL_STRAIGHT_FLUSH;

				groupedCard.clear();
				groupedCard.addAll(hand);
			}
		}

		//数値判定


		//return new PokerJudgedHand(resultHand, resultNumber, resultMark);//result;
		return new PokerJudgeResult(resultHand, groupedCard);

	}

	private Map<Object, List<Card>> makeHandGroupByNumber(List<Card> hand) {
		//手札の番号によるストリーム生成
		//int[] handNums = new int[hand.length];
		//IntStream.range(0, hand.length).forEach(i -> handNums[i] = hand[i].getNumber());

		//番号によってグループ分け
		Stream<Card> handStream = hand.stream();
		Map<Object, List<Card>> handGroup = handStream.collect(Collectors.groupingBy(i -> i.getNumber()));

		return handGroup;
	}

	private Map<Object, List<Card>> makeHandGroupByMark(List<Card> hand) {
		//手札の番号によるストリーム生成
		//int[] handNums = new int[hand.length];
		//IntStream.range(0, hand.length).forEach(i -> handNums[i] = hand[i].getNumber());

		//番号によってグループ分け
		Stream<Card> handStream = hand.stream();
		Map<Object, List<Card>> handGroup = handStream.collect(Collectors.groupingBy(i -> i.getMark()));

		return handGroup;
	}


	/*
	 * 役の判定を行うメソッド
	 */
	private boolean isOnePair(List<Card> hand) {
		//2枚の組が1つだけある場合にtrueを返す
		if(makeHandGroupByNumber(hand).values().stream().filter(i -> i.size() == 2).count() == 1) {
			return true;
		}
		return false;
	}

	private boolean isOnePairCaseJoker(List<Card> hand) {
		List<Card> tempHand =
				hand.stream()
					.filter(card -> card.getMark() != Card.JOKER_MARK
									&& card.getNumber()!= Card.JOKER_NUMBER)
					.collect(Collectors.toList());
		return ((PokerJudgeResult) judge(tempHand)).getHandNum() == NO_PAIR;
	}

	private boolean isTwoPair(List<Card> hand) {
		if(makeHandGroupByNumber(hand).values().stream().filter(i -> i.size() == 2).count() == 2) {
			return true;
		}

		//JokerPattern:Jokerを含むツーペアはありえないので非実装

		return false;
	}

	private boolean isThreeOfAKind(List<Card> hand) {
		//3枚の組が1つだけある場合にtrueを返す
		if(makeHandGroupByNumber(hand).values().stream().filter(i -> i.size() == 3).count() == 1) {
			return true;
		}

		return false;
	}

	private boolean isThreeOfAKindCaseJoker(List<Card> hand) {
		List<Card> tempHand =
				hand.stream()
					.filter(card -> card.getMark() != Card.JOKER_MARK
									&& card.getNumber()!= Card.JOKER_NUMBER)
					.collect(Collectors.toList());
		return ((PokerJudgeResult)judge(tempHand)).getHandNum() == ONE_PAIR;
	}

	//*
	private boolean isStraight(List<Card> hand) {

		boolean result = false;
		//roopStyle(関数型に直したい...)
		Collections.sort(hand, Comparator.comparing(Card::getNumber));

		result = IntStream.range(1, hand.size()).allMatch(i -> hand.get(i).getNumber() - hand.get(i-1).getNumber() == 1);


		//手札にAが含まれるとき、Aを1として再計算
		if(hand.stream().anyMatch(i -> i.getNumber() == 14)) {
			hand.stream().filter(i -> i.getNumber() == 14).forEach(i -> i.setTempNumber(1));
			result = isStraight(hand);
			hand.stream().filter(i -> i.getNumber() == 1).forEach(i -> i.setTempNumber(14));
		}
		return result;
	}
	//*/

	private boolean isStraightCaseJoker(List<Card> hand) {
		List<Card> tempHand =
				hand.stream()
					.filter(card -> card.getMark() != Card.JOKER_MARK
									&& card.getNumber()!= Card.JOKER_NUMBER)
					.collect(Collectors.toList());
		if(isStraight(tempHand)) {
			return true;
		}
		boolean result = IntStream.range(1, tempHand.size()).filter(i -> tempHand.get(i).getNumber() - tempHand.get(i-1).getNumber() == 2).count() == 1 &&
							IntStream.range(1, tempHand.size()).filter(i -> tempHand.get(i).getNumber() - tempHand.get(i-1).getNumber() > 2).count() == 0 &&
								tempHand.stream().distinct().count() == tempHand.size();
		if(result == false) {
			hand.stream().filter(i -> i.getNumber() == 14).forEach(i -> i.setTempNumber(1));
			result = IntStream.range(1, tempHand.size()).filter(i -> tempHand.get(i).getNumber() - tempHand.get(i-1).getNumber() == 2).count() == 1 &&
						IntStream.range(1, tempHand.size()).filter(i -> tempHand.get(i).getNumber() - tempHand.get(i-1).getNumber() > 2).count() == 0 &&
						tempHand.stream().distinct().count() == tempHand.size();
		}
		return result;
	}

	private boolean isFlush(List<Card> hand) {
		return makeHandGroupByMark(hand).keySet().size() == 1;
	}

	private boolean isFlushCaseJoker(List<Card> hand) {
		List<Card> tempHand =
				hand.stream()
					.filter(card -> card.getMark() != Card.JOKER_MARK
									&& card.getNumber()!= Card.JOKER_NUMBER)
					.collect(Collectors.toList());
		return isFlush(tempHand);
	}

	private boolean isFullHouse(List<Card> hand) {
		return isOnePair(hand) && isThreeOfAKind(hand);
	}

	private boolean isFullHouseCaseJoker(List<Card> hand) {
		List<Card> tempHand =
				hand.stream()
					.filter(card -> card.getMark() != Card.JOKER_MARK
									&& card.getNumber()!= Card.JOKER_NUMBER)
					.collect(Collectors.toList());
		return isTwoPair(tempHand);
	}

	private boolean isFourOfAKind(List<Card> hand) {
		//4枚の組が1つだけある場合にtrueを返す
		return makeHandGroupByNumber(hand).values().stream().filter(i -> i.size() == 4).count() == 1;
	}

	private boolean isFourOfAKindCaseJoker(List<Card> hand) {
		List<Card> tempHand =
				hand.stream()
					.filter(card -> card.getMark() != Card.JOKER_MARK
									&& card.getNumber()!= Card.JOKER_NUMBER)
					.collect(Collectors.toList());
		return isThreeOfAKind(tempHand);
	}

	private boolean isStraightFlush(List<Card> hand) {
		return isStraight(hand) && isFlush(hand);
	}

	private boolean isStraightFlushCaseJoker(List<Card> hand) {
		return isStraightCaseJoker(hand) && isFlushCaseJoker(hand);
	}

	private boolean isRoyalStraightFlush(List<Card> hand) {
		return isStraightFlush(hand) && hand.stream().allMatch(i -> i.getMark() == Card.SPADE)
										&& hand.stream().allMatch(i -> i.getNumber()/10 > 0);
	}

	private boolean isRoyalStraightFlushCaseJoker(List<Card> hand) {
		List<Card> tempHand =
				hand.stream()
					.filter(card -> card.getMark() != Card.JOKER_MARK
									&& card.getNumber() != Card.JOKER_NUMBER)
					.collect(Collectors.toList());
		return isStraightFlushCaseJoker(hand) && tempHand.stream().allMatch(i -> i.getMark() == Card.SPADE)
				&& tempHand.stream().allMatch(i -> i.getNumber()/10 > 0);
	}


}

class PokerJudgeResult extends JudgeResult{
	private int hand;//手札の役
	private List<Card> groupedCards;//役の構成
	private String handName;//役の名称

	PokerJudgeResult(int hand, List<Card> groupedCards){
		this.hand = hand;
		this.groupedCards = groupedCards;
		this.handName = PokerJudger.pokerHandMap.get(hand);
	}

	int getHandNum() {
		return hand;
	}

	List<Card> getGroupedCards(){
		return groupedCards;
	}


	@Override
	public String getResult() {
		// TODO 自動生成されたメソッド・スタブ
		return handName;
	}

	@Override
	public void setResult(List<Card> hand) {
		// TODO 自動生成されたメソッド・スタブ
	}
}


