package ga.unnikked.expressionevaluator.utils;

import java.util.HashMap;

public class DoubleHashMap<K, V> extends HashMap<K, V> {
	private HashMap<V, K> reversed = new HashMap<V, K>();

	@Override
	public V put(K key, V value) {
		V v = super.put(key, value);
		reversed.put(value, key);
		return v;
	}

	@Override
	public V remove(Object key) {
		throw new UnsupportedOperationException();
	}

	public K getValue(V key) {
		return reversed.get(key);
	}
}