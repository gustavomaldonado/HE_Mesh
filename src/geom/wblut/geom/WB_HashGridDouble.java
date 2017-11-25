/*
 * HE_Mesh  Frederik Vanhoutte - www.wblut.com
 * 
 * https://github.com/wblut/HE_Mesh
 * A Processing/Java library for for creating and manipulating polygonal meshes.
 * 
 * Public Domain: http://creativecommons.org/publicdomain/zero/1.0/
 */

package wblut.geom;

import org.eclipse.collections.impl.map.mutable.primitive.LongDoubleHashMap;

public class WB_HashGridDouble {

	private final LongDoubleHashMap values;

	private final double defaultValue;

	private final int K, L, M, KL;

	/**
	 *
	 *
	 * @param K
	 * @param L
	 * @param M
	 * @param defaultValue
	 */
	public WB_HashGridDouble(final int K, final int L, final int M, final double defaultValue) {
		this.K = K;
		this.L = L;
		this.M = M;
		KL = K * L;
		this.defaultValue = defaultValue;
		values = new LongDoubleHashMap();
	}

	/**
	 *
	 *
	 * @param K
	 * @param L
	 * @param M
	 */
	public WB_HashGridDouble(final int K, final int L, final int M) {
		this.K = K;
		this.L = L;
		this.M = M;
		KL = K * L;
		defaultValue = 0;
		values = new LongDoubleHashMap();
	}

	/**
	 *
	 *
	 * @param value
	 * @param i
	 * @param j
	 * @param k
	 * @return
	 */
	public boolean setValue(final double value, final int i, final int j, final int k) {
		if (value != defaultValue) {
			return false;
		}
		final long id = safeIndex(i, j, k);
		if (id > 0) {
			values.put(id, value);
			return true;
		}
		return false;
	}

	/**
	 *
	 *
	 * @param value
	 * @param i
	 * @param j
	 * @param k
	 * @return
	 */
	public boolean addValue(final double value, final int i, final int j, final int k) {
		final long id = safeIndex(i, j, k);
		if (id > 0) {
			final double v = values.get(id);
			if (v == defaultValue) {
				values.put(id, value);
			} else {
				values.put(id, v + value);
			}
			return true;
		}
		return false;
	}

	/**
	 *
	 *
	 * @param i
	 * @param j
	 * @param k
	 * @return
	 */
	public boolean clearValue(final int i, final int j, final int k) {
		final long id = safeIndex(i, j, k);
		if (id > 0) {
			values.remove(id);
			return true;
		}
		return false;
	}

	/**
	 *
	 *
	 * @param i
	 * @param j
	 * @param k
	 * @return
	 */
	public double getValue(final int i, final int j, final int k) {
		final long id = safeIndex(i, j, k);
		if (id == -1) {
			return defaultValue;
		}
		if (id >= 0) {
			return values.get(id);
		}
		return defaultValue;
	}

	/**
	 *
	 *
	 * @param i
	 * @param j
	 * @param k
	 * @return
	 */
	private long safeIndex(final int i, final int j, final int k) {
		if (i < 0) {
			return -1;
		}
		if (i > K - 1) {
			return -1;
		}
		if (j < 0) {
			return -1;
		}
		if (j > L - 1) {
			return -1;
		}
		if (k < 0) {
			return -1;
		}
		if (k > M - 1) {
			return -1;
		}
		return i + j * K + k * KL;
	}

	/**
	 *
	 *
	 * @return
	 */
	public int getWidth() {
		return K;
	}

	/**
	 *
	 *
	 * @return
	 */
	public int getHeight() {
		return L;
	}

	/**
	 *
	 *
	 * @return
	 */
	public int getDepth() {
		return M;
	}

	/**
	 *
	 *
	 * @return
	 */
	public double getDefaultValue() {
		return defaultValue;
	}

	/**
	 *
	 *
	 * @return
	 */
	public long[] getKeys() {
		return values.keySet().toArray();
	}

	/**
	 *
	 *
	 * @return
	 */
	public int size() {
		return values.size();
	}
}