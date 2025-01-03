public class Main {
    public static void main(String[] args) {
        //Test cases

        ShiftPoint shiftPoint = new ShiftPoint(10000000, 5);

        System.out.println(shiftPoint.shiftIndex());// 10000000

        shiftPoint.setK(4);
        System.out.println(shiftPoint.shiftIndex()); //4


        shiftPoint.setK(0);
        System.out.println(shiftPoint.shiftIndex()); //0

        shiftPoint.setK(100);
        System.out.println(shiftPoint.shiftIndex()); //100

        //if s is 0 there is no shift
        shiftPoint.setS(0);
        System.out.println(shiftPoint.shiftIndex()); //0


        shiftPoint.setS(-5);
        System.out.println(shiftPoint.shiftIndex()); //100

        shiftPoint.setS(0);
        shiftPoint.setK(0);
        System.out.println(shiftPoint.shiftIndex());  //0

        ShiftPointWithDifferentMethod shiftPointWithDifferentMethod = new ShiftPointWithDifferentMethod(4,5);

        System.out.println(shiftPointWithDifferentMethod.shiftIndex()); //4


    }

}


class ShiftPointWithDifferentMethod extends ShiftPoint {

    public ShiftPointWithDifferentMethod(int k, int S) {
        super(k, S);
    }

    public long f(long i) {
        return 2 * i +5;
    }


}

class ShiftPoint {


    long k = 4;
    long S = 5;
    long MAX = 1_000_000_000_000L; //length of array(if any, by default 10^12)

    public void setK(int k) {
        this.k = k;
    }

    public void setS(int s) {
        S = s;
    }

    public ShiftPoint(int k, int S) {
        this.k = k;
        this.S = S;
    }

    public ShiftPoint() {
    }

    public long f(long i) {
        return 2 * i;
    }

    private long getValue(long idx) {
        return idx >= k ? f(idx) + S : f(idx);
    }

    public long shiftIndex() {
        long diff;
        long diff1 = 0, diff2 = 0, diff3 = 0;
        diff1 = getValue(1) - getValue(0);
        diff2 = getValue(2) - getValue(1);
        diff3 = getValue(3) - getValue(2);

        if (diff1 == diff2) {
            if (diff2 != diff3) {
                return 3;
            }

        } else if (diff2 == diff3) {
            return 1;
        }
        
        diff = diff1;
        long st = 2, end = 4;
        while (st <= end && end <= MAX) {
            try {
                long mid = st + (end - st) / 2;
                if (getValue(mid + 1) - getValue(mid) != getValue(mid) - getValue(mid - 1))
                    return mid + 1;

                long numbers = end - st + 1;
                long expectedDiff = numbers * diff;
                long actualDiff = getValue(end) - getValue(st - 1);
                if (expectedDiff == actualDiff) {
                    st = end;
                    end = end << 1;
                } else {
                    //found in this range
                    long leftExpectedDiff = diff * (mid - st + 1);
                    long leftActualDiff = getValue(mid) - getValue(st - 1);
                    if (leftActualDiff == leftExpectedDiff) {
                        st = mid + 1;
                    } else {
                        end = mid - 1;
                    }
                }
            } catch (IndexOutOfBoundsException iox) {
                return 0;
            }
        }
        return 0L;
    }
}

