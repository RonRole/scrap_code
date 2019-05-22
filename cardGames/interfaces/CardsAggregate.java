package interfaces;

import iterators.*;

//カードの集合を持つことを示すインターフェース
public interface CardsAggregate {
	public CardListIterator iterator();
}
