/**
 * An implementation of ExpansionCodeBookInterface using an array.
 */

public class ArrayCodeBook implements ExpansionCodeBookInterface {
    private static final int R = 256; // alphabet size
    private String[] codebook;
    private int W; // current codeword width
    private int minW; // minimum codeword width
    private int maxW; // maximum codeword width
    private int L; // maximum number of codewords with
                   // current codeword width (L = 2^W)
    private int code; // next available codeword value

    public ArrayCodeBook(int minW, int maxW) {
        this.maxW = maxW;
        this.minW = minW;
        initialize();
    }

    public int size() {
        return code;
    }

    public int getCodewordWidth(boolean flushIfFull) {
        if (code < L) {
            return W;
        } else {
            if (W != maxW) {
                return W + 1;
            } else {
                if (flushIfFull) {
                    return minW;
                } else {
                    return maxW;
                }
            }
        }
    }

    private void initialize() {
        codebook = new String[1 << maxW];
        W = minW;
        L = 1 << W;
        code = 0;
        // initialize symbol table with all 1-character strings
        for (int i = 0; i < R; i++)
            add("" + (char) i, false);
        add("", false); // R is codeword for EOF
    }

    public void add(String str, boolean flushIfFull) {
        boolean haveRoom = false;

        if(code < L){
            haveRoom = true;
          }
          if (!haveRoom) {
            if (W < maxW) {
              W++;
              L *= 2;
              haveRoom = true;
            } else if (W == maxW) {
              if (flushIfFull) {
                initialize();
                haveRoom = true;
              } else {
                haveRoom = false;
              }
            }
          }

        if(haveRoom){
            codebook[code] = str;
            //System.err.println(str + " " + code + " W=" + W);
            code++;
        }
    }

    public String getString(int codeword) {
        return codebook[codeword];
    }

}