package hr.freskov.fuzzy;

/**
 * Utility class offering standard membership functions used in fuzzy sets.
 * 
 * @author freskov
 * @version 1.0
 */
public class StandardFuzzySets {

	private StandardFuzzySets() {
	}

	/**
	 * L-function implementation.
	 * 
	 * @param begin
	 *            begin index
	 * @param end
	 *            end index
	 * @return L-function.
	 */
	public static IIntUnaryFunction lFunction(int begin, int end) {
		if (begin > end) {
			throw new IllegalArgumentException("Begin index should be smaller or equal to end index.");
		}

		return index -> Math.min(1.0, Math.max(0.0, (double) (end - index) / (end - begin)));
		
	}

	/**
	 * Gamma-function implementation.
	 * 
	 * @param begin
	 *            begin index
	 * @param end
	 *            end index
	 * @return Gamma-function.
	 */
	public static IIntUnaryFunction gammaFunction(int begin, int end) {
		if (begin > end) {
			throw new IllegalArgumentException("Begin index should be smaller or equal to end index.");
		}

		return index -> Math.min(1.0, Math.max(0.0, (double) (index - begin) / (end - begin)));
	}

	/**
	 * Lambda-function implementation.
	 * 
	 * @param begin
	 *            begin index
	 * @param peak
	 *            peak index
	 * @param end
	 *            end index
	 * @return Lambda-function.
	 */
	public static IIntUnaryFunction lambdaFunction(int begin, int peak, int end) {
		if (begin > peak) {
			throw new IllegalArgumentException("Begin index should be smaller or equal to peak index.");
		}
		if (peak > end) {
			throw new IllegalArgumentException("Peak index should be smaller or equal to end index.");
		}
		return index -> {
			double value = index < peak ? (double) (index - begin) / Math.max(peak - begin, 1)
										: (double) (end - index) / Math.max(end - peak, 1);
			return Math.min(1.0, Math.max(0.0, value));
		};
	}

	/**
	 * Pi-function implementation.
	 * 
	 * @param begin
	 *            begin index
	 * @param peakBegin
	 *            peak begin index
	 * @param peakEnd
	 *            peakEnd index
	 * @param end
	 *            end index
	 * @return Pi-function.
	 */
	public static IIntUnaryFunction piFunction(int begin, int peakBegin, int peakEnd, int end) {
		if (begin > peakBegin) {
			throw new IllegalArgumentException("Begin index should be smaller or equal to peak begin index.");
		}
		if (peakBegin > peakEnd) {
			throw new IllegalArgumentException("Peak begin index should be smaller or equal to peak end index.");
		}
		if (peakEnd > end) {
			throw new IllegalArgumentException("Peak end index should be smaller or equal to end index.");
		}
		return index -> {
			double value = index < peakBegin ? (double) (index - begin) / Math.max(peakBegin - begin, 1)
											: (double) (end - index) / Math.max(end - peakEnd, 1);
			return Math.min(1.0, Math.max(0.0, value));
		};
	}

}
