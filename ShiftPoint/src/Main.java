public class Main {
    public static void main(String[] args) {
        //Test cases

        ShiftPoint shiftPoint = new ShiftPoint(10000000, 5);

        assert shiftPoint.shiftIndex() == 10000000;

        shiftPoint.setK(0);
        assert shiftPoint.shiftIndex() == 0;

        shiftPoint.setK(100);
        assert shiftPoint.shiftIndex() == 100;

        //if s is 0 there is no shift
        shiftPoint.setS(0);
        assert shiftPoint.shiftIndex() == 0;


        shiftPoint.setS(-5);
        assert shiftPoint.shiftIndex() == 100;

        shiftPoint.setS(0);
        shiftPoint.setK(0);
        assert shiftPoint.shiftIndex()==0;


    }

}

class ShiftPoint {


    long k = 4;
    long S = 5;

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

    private long f(long i) {
        return 2 * i;
    }

    private long getValue(long idx) {
        return idx >= k ? f(idx) + S : f(idx);
    }

    public long shiftIndex() {
        long diff;
        long diff1 = 0, diff2 = 0, diff3 = 0;
        diff1 = getValue(1) - getValue(0); //2
        diff2 = getValue(2) - getValue(1);// 7
        diff3 = getValue(3) - getValue(2);//

        if (diff1 == diff2) {
            if (diff2 != diff3) {
                return 3;
            }

        } else if (diff2 == diff3) {
            return 1;
        }
        long MAX = 1_000_000_000_000L;
        diff = diff1;
        long st = 2, end = 4;
        while (st <= end && end < MAX) {
            try {
                long mid = st + (end - st) / 2;
                if (getValue(mid + 1) - getValue(mid) != getValue(mid) - getValue(mid - 1))
                    return mid + 1;

                long numbers = end - st + 1;
                long expectedDiff = numbers * diff;
                long actualDiff = getValue(end) - getValue(st - 1);
                if (expectedDiff == actualDiff) {
                    st = end;
                    end = end << 2;
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


/*

0   1   2   3   4    5    6    7    8    9

f(x)

1
8
27



g(i) = f(i)  i<k

g(i) = f(i)+ S i>=k






0 1 2 4 8 16 32 ...


 */