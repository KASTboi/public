import java.util.*;

public class q2 {


     // S-box Tables
     public static int[][][] sbox = {
        { { 14, 4, 13, 1, 2, 15, 11, 8, 3, 10, 6, 12, 5, 9, 0, 7 },
          { 0, 15, 7, 4, 14, 2, 13, 1, 10, 6, 12, 11, 9, 5, 3, 8 },
          { 4, 1, 14, 8, 13, 6, 2, 11, 15, 12, 9, 7, 3, 10, 5, 0 },
          { 15, 12, 8, 2, 4, 9, 1, 7, 5, 11, 3, 14, 10, 0, 6, 13 } }, // s1

        { { 15, 1, 8, 14, 6, 11, 3, 4, 9, 7, 2, 13, 12, 0, 5, 10 },
          { 3, 13, 4, 7, 15, 2, 8, 14, 12, 0, 1, 10, 6, 9, 11, 5 },
          { 0, 14, 7, 11, 10, 4, 13, 1, 5, 8, 12, 6, 9, 3, 2, 15 },
          { 13, 8, 10, 1, 3, 15, 4, 2, 11, 6, 7, 12, 0, 5, 14, 9 } },//s2

        { { 10, 0, 9, 14, 6, 3, 15, 5, 1, 13, 12, 7, 11, 4, 2, 8 },
          { 13, 7, 0, 9, 3, 4, 6, 10, 2, 8, 5, 14, 12, 11, 15, 1 },
          { 13, 6, 4, 9, 8, 15, 3, 0, 11, 1, 2, 12, 5, 10, 14, 7 },
          { 1, 10, 13, 0, 6, 9, 8, 7, 4, 15, 14, 3, 11, 5, 2, 12 } }, // s3

        { { 7, 13, 14, 3, 0, 6, 9, 10, 1, 2, 8, 5, 11, 12, 4, 15 },
          { 13, 8, 11, 5, 6, 15, 0, 3, 4, 7, 2, 12, 1, 10, 14, 9 },
          { 10, 6, 9, 0, 12, 11, 7, 13, 15, 1, 3, 14, 5, 2, 8, 4 },
          { 3, 15, 0, 6, 10, 1, 13, 8, 9, 4, 5, 11, 12, 7, 2, 14 } },//s4

        { { 2, 12, 4, 1, 7, 10, 11, 6, 8, 5, 3, 15, 13, 0, 14, 9 },
          { 14, 11, 2, 12, 4, 7, 13, 1, 5, 0, 15, 10, 3, 9, 8, 6 },
          { 4, 2, 1, 11, 10, 13, 7, 8, 15, 9, 12, 5, 6, 3, 0, 14 },
          { 11, 8, 12, 7, 1, 14, 2, 13, 6, 15, 0, 9, 10, 4, 5, 3 } },//s5

        { { 12, 1, 10, 15, 9, 2, 6, 8, 0, 13, 3, 4, 14, 7, 5, 11 },
          { 10, 15, 4, 2, 7, 12, 9, 5, 6, 1, 13, 14, 0, 11, 3, 8 },
          { 9, 14, 15, 5, 2, 8, 12, 3, 7, 0, 4, 10, 1, 13, 11, 6 },
          { 4, 3, 2, 12, 9, 5, 15, 10, 11, 14, 1, 7, 6, 0, 8, 13 } },//s6

        { { 4, 11, 2, 14, 15, 0, 8, 13, 3, 12, 9, 7, 5, 10, 6, 1 },
          { 13, 0, 11, 7, 4, 9, 1, 10, 14, 3, 5, 12, 2, 15, 8, 6 },
          { 1, 4, 11, 13, 12, 3, 7, 14, 10, 15, 6, 8, 0, 5, 9, 2 },
          { 6, 11, 13, 8, 1, 4, 10, 7, 9, 5, 0, 15, 14, 2, 3, 12 } },//s7

        { { 13, 2, 8, 4, 6, 15, 11, 1, 10, 9, 3, 14, 5, 0, 12, 7 },
          { 1, 15, 13, 8, 10, 3, 7, 4, 12, 5, 6, 11, 0, 14, 9, 2 },
          { 7, 11, 4, 1, 9, 12, 14, 2, 0, 6, 10, 13, 15, 3, 5, 8 },
          { 2, 1, 14, 7, 4, 10, 8, 13, 15, 12, 9, 0, 3, 5, 6, 11 } }//s8
      };


//   Permutation Table 1
static final int[]  PC1 = { 57, 49, 41, 33, 25,
    17, 9, 1, 58, 50, 42, 34, 26,
    18, 10, 2, 59, 51, 43, 35, 27,
    19, 11, 3, 60, 52, 44, 36, 63,
    55, 47, 39, 31, 23, 15, 7, 62,
    54, 46, 38, 30, 22, 14, 6, 61,
    53, 45, 37, 29, 21, 13, 5, 28,
    20, 12, 4 };

//   Permutation Table 2
static final int[]  PC2 = { 14, 17, 11, 24, 1, 5, 3,
    28, 15, 6, 21, 10, 23, 19, 12,
    4, 26, 8, 16, 7, 27, 20, 13, 2,
    41, 52, 31, 37, 47, 55, 30, 40,
    51, 45, 33, 48, 44, 49, 39, 56,
    34, 53, 46, 42, 50, 36, 29, 32 }; 

// Initial Permutation Table
   static final int[] IP = {
    58, 50, 42, 34, 26, 18, 10, 2,
    60, 52, 44, 36, 28, 20, 12, 4,
    62, 54, 46, 38, 30, 22, 14, 6,
    64, 56, 48, 40, 32, 24, 16, 8,
    57, 49, 41, 33, 25, 17,  9, 1,
    59, 51, 43, 35, 27, 19, 11, 3,
    61, 53, 45, 37, 29, 21, 13, 5,
    63, 55, 47, 39, 31, 23, 15, 7
 }; 

 //inverse of IP
 static final int[] IP1 = { 
    40, 8, 48, 16, 56, 24, 64,
    32, 39, 7, 47, 15, 55,
    23, 63, 31, 38, 6, 46,
    14, 54, 22, 62, 30, 37,
    5, 45, 13, 53, 21, 61,
    29, 36, 4, 44, 12, 52,
    20, 60, 28, 35, 3, 43,
    11, 51, 19, 59, 27, 34,
    2, 42, 10, 50, 18, 58,
    26, 33, 1, 41, 9, 49,
    17, 57, 25 }; 

   static final  int[] SHIFTS = { 1, 1, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 1 };  
    
    
    // Expansion Table
    static final int[] EP = { 32, 1, 2, 3, 4, 5, 4,
                            5, 6, 7, 8, 9, 8, 9, 10,
                            11, 12, 13, 12, 13, 14, 15,
                            16, 17, 16, 17, 18, 19, 20,
                            21, 20, 21, 22, 23, 24, 25,
                            24, 25, 26, 27, 28, 29, 28,
                            29, 30, 31, 32, 1 };


     //Permutation Table
    static final int[] P = { 16, 7, 20, 21, 29, 12, 28,
                            17, 1, 15, 23, 26, 5, 18,
                            31, 10, 2, 8, 24, 14, 32,
                            27, 3, 9, 19, 13, 30, 6,
                            22, 11, 4, 25 };



    public static void main(String[] args) {

        // defult file names if args are not given
        String keyFileName = "key.txt";
        String decodedFileName = "decodedOutPut.txt";
        String encodedFileNAme = "encodedOutPut.txt";
        String inPutFileName = "input.txt";
        
        //file names if args are given
        if(args.length == 4){
            try{
                inPutFileName = args[0] + ".txt";
                keyFileName = args[1] + ".txt";
                encodedFileNAme = args[2] + ".txt";
                decodedFileName = args[3] + ".txt";
            }
            catch(Exception e){
                System.out.print(e.getMessage());
                System.out.print("the input or key files do not exist");
            }
            
        }
      

        String [] input = IO.readFile(inPutFileName);
        String[] binary = convertInputToBinary(input);
        String key = getKey(keyFileName);


        // encodeing ********************************
        String[] encoded = new String[binary.length];
        for(int i=0;i<binary.length;i++){
            encoded[i] = encode(binary[i], key);
           
        }
        // done encoding *****************************

        saveEncodedToHexFile(encoded, encodedFileNAme); // saves the encoded binary as hex to encodedFileNAme
        
   
        // decoding ************************************
        encoded = readEncodedHexFile(encodedFileNAme);

        String[] decoded = new String[encoded.length];
        for(int i=0;i<encoded.length;i++){
            decoded[i] = decode(encoded[i], key);
        }
        String[] plainText = binaryToString(decoded);
        for(String s : plainText){
            IO.writeRow(s, decodedFileName);
        }
        // done decoding **********************************
    }

    /***************************************************** 
        imports: plain text as binary, key
        purpse: calls all nessasary functions for encodeing 
        exports: encrypted binary of input
    *******************************************************/
    public static String encode(String input, String key){

        String[] blocks = splitInto64BitBlocks(input); // splits text into blocks of 64 bytes
        String[] encypedBlocks = new String[blocks.length];
        String[] subKeys = getSubkeys(key); // splits key into 16 subkeys 
        for(int i=0; i<blocks.length;i++){
           encypedBlocks[i] = rounds(blocks[i], subKeys);
        }
        String outPut = turnBlocksIntoString(encypedBlocks); // reconects blocks 

        return outPut;
    }

    /***************************************************** 
        imports: encrypted text as binary string , key
        purpse: calls all nessasary functions for decoding 
        exports: decrypted binary string
    *******************************************************/
    public static String decode(String input, String key){
        String[] blocks = splitInto64BitBlocks(input); // splits text into blocks of 64 bytes
        String[] decypedBlocks = new String[blocks.length];

        String[] subKeys = reverseArray(getSubkeys(key)); // splits key into 16 subkeys in reverse order for decrytion


        for(int i=0; i<blocks.length;i++){
            decypedBlocks[i] = rounds(blocks[i], subKeys);  // rounds function is called on one block at a time
        }
        String outPut = turnBlocksIntoString(decypedBlocks); // reconects blocks 

        return outPut;
    }

    /***************************************************** 
        imports: key
        purpse:  splits key into 16 subKeys 
        exports: array of 16 subKeys
    *******************************************************/ 
    public static String[] getSubkeys(String key){

        String keys[] = new String[16];
        key = permutation(PC1, key);

        for (int i=0; i<16;i++) {

            String left = key.substring(0, (key.length()/2));
            String right = key.substring((key.length()/2)); // splits in half

            key = leftCircularShift(left, SHIFTS[i])+ leftCircularShift(right,SHIFTS[i]);

            // second key permutation
            keys[i] = permutation(PC2, key);
        }
        return keys;
    }

     /***************************************************** 
        imports: subKey, number of bits to shift 
        purpse:  shifts key to left by numBits 
        exports: shifted subKey
    *******************************************************/ 
    public static String leftCircularShift(String input, int numBits)
    {
        char[]inC = input.toCharArray();
        for(int i = 0; i < numBits; i++){  
            char first; 
            int j; 
            //Stores the first element of the array  
            first = inC[0];  
            for(j = 0; j < inC.length-1; j++){  
                //Shift element of array by one  
                inC[j] = inC[j+1];  
            }  
            //First element of array will be added to the end  
            inC[j] = first;
        }  
        String str = new String(inC);
        return str;
    } 

     /***************************************************** 
        imports: String , shift table 
        purpse:  rearange a string acouding to a permutation table  
        exports: permuted String (string will be size if table.length)
    *******************************************************/ 
    public static String permutation(int[] table, String input)
    {
        String output = "";
        for (int i = 0; i < table.length; i++)
            output += input.charAt(table[i] - 1);
        return output;
    }

     /***************************************************** 
        imports: name of file containing key
        purpse:  calls Cipherkey() to pad or chop if nessasary  
        exports: returns 64bit key
    *******************************************************/
    public static String getKey(String file){
      String[] input = IO.readFile(file);
      String key = input[0];
      key = Cipherkey(key);
      return key;

    }

      /***************************************************** 
        imports: binary input block (64bits)
        purpse:  does inital permutation on block, splits block into left and right, 
                 xors left with output of F fuction key and right, swaps halfs 
                 does inverse permutation.  
        exports: binary block encoded/decoded (64bits)
    *******************************************************/ 
    public static String rounds(String input, String[] subkeys){

        input = permutation(IP, input); // inital permutation 

        String left = input.substring(0, (input.length()/2));
        String right = input.substring((input.length()/2)); // splits in half
        String temp;

        for(int i=0;i< 16;i++){
            left = xorStrings(f(right,subkeys[i]), left);
            temp = left;
            left = right;
            right = temp;
        }

        temp = left; 
        left = right;
        right = temp;  // switch for last time

        input = left + right;

        input = permutation(IP1, input); // inverse of inital permutation 
        return input;


    }

      /***************************************************** 
        imports: right side from rounds (32bits), key
        purpse: do expantion permutaion on input, 
                xor input with key,
                split into array of 6bit parts,
                use Sbox to turn each part into 4bits,
                reconect the partts into a 32bit block,
                does permutation of bock and P
        exports: 32 bit block
    *******************************************************/
    public static String f(String input, String subKey){
        input = permutation(EP, input); // expand 32bit to 48bit

        input = xorStrings(input, subKey);

        String[] splits = input.split("(?<=\\G.{" + 6 + "})");

        String output = "";
        for(int i=0;i<splits.length;i++){

            String Srow = "";
            String Scol = "";
            Srow = Srow + splits[i].charAt(0) + splits[i].charAt(5);
            Scol = Scol + splits[i].charAt(1) + splits[i].charAt(2) + splits[i].charAt(3) + splits[i].charAt(4);

            int row = binToDec(Srow);
            int col = binToDec(Scol);

            int SboxReturn = sbox[i][row][col];
            output = output + intToBinary(SboxReturn); 

        }

        output = permutation(P, output);
    
        return output;
    }

      /***************************************************** 
        imports: key of any size 
        purpse:  reads first line of file, padds or chops key as nessasry  
        exports: returns 64bit key
    *******************************************************/
    public static String Cipherkey(String key){
        key = StringToBinary(key);

        if(key.length()<64){//pad with 0 bits to make up to 64 bits 
            int amount = (64 - key.length());
            key = key + String.join("", Collections.nCopies(amount, "0"));//adds 0s to make the key 64 bits long
        }

        if(key.length()>64){// shorten key to 64 bits
            key = key.substring(0, 64);// takes first 64 chars discards the rest
        } 
        return key;
    }

  /***************************************************** 
        imports: string of bits
        purpse: splits into arrays of 64 bits 
                if last lot of bits is too short than pad with 0s
        exports: array of 64bit blocks 
    *******************************************************/ 
    public static String[] splitInto64BitBlocks(String input){
       
        String[] blocks = input.split("(?<=\\G.{64})"); // splits input at every 64th char

        if(blocks[blocks.length-1].length() != 64){ // check length of last block and adds padding if needed 
            String padding = "00000000";
            int n = (64 - blocks[blocks.length-1].length()) / 8; // number of padding blocks needed 

            for(int i=0;i<n;i++){
                blocks[blocks.length-1] = blocks[blocks.length-1] + padding;
            }
        }
        return blocks;
    }

    public static String turnBlocksIntoString(String[] blocks){
        String delimiter = "";
        String combind = String.join(delimiter, blocks);
        return combind;
    }

      /* **********************************************************************************

      imports: file as array of strings
      purpse:   splits each char and coverts into ascii value,
                turns ascii value to binary with padding 0's   
    exports: output an array of strings where each string is a line and each block of 8 bits is a char
    *****************************************************************************/
    public static String[] convertInputToBinary(String[] input){
        
        for(int i=0; i<input.length;i++){
            StringBuilder sb = new StringBuilder();

            char[] chars = input[i].toCharArray();
            for (char c : chars) {
                String binary = Integer.toBinaryString(c); // takes the ascii value of the char and converts to binary
                String formatted = String.format("%8s", binary);
                String output = formatted.replaceAll(" ", "0"); //"%8s" splits onto blocks of 8 bits with spaces then replaces the spaces with padding 0s
                sb.append(output);
                input[i] = sb.toString();
            }
        }
        return input;
    }  

    public static String StringToBinary(String input){
        StringBuilder sb = new StringBuilder();

        char[] chars = input.toCharArray();
        for (char c : chars) {
            String binary = Integer.toBinaryString(c); // takes the ascii value of the char and converts to binary
            String formatted = String.format("%8s", binary);
            String output = formatted.replaceAll(" ", "0"); //"%8s" splits onto blocks of 8 bits with spaces then replaces the spaces with padding 0s
            sb.append(output);
            //sb.append(" "); // adds spaces after each block of 8 bits
            input = sb.toString();
        }
        return input;
  }

    public static String intToBinary(int in){
        String binary = Integer.toBinaryString(in);
        while(binary.length()<4){
            binary = "0" + binary;
        }
        
        return binary;
    }


 
    public static String[] binaryToString(String[] binary){
    
        for(int i=0; i<binary.length;i++){
            String s = "";
            for(int index = 0; index < binary[i].length(); index+=8){
                boolean padding = false;
                String temp = binary[i].substring(index, index+8); // takes 8 chars at into a string 
                if(temp.equals("00000000")){ // checking if a part if the decoded text is padding 
                    padding = true;
                }
                int num = Integer.parseInt(temp,2); //turn binary to int value
                char letter = (char) num; // convert int value to ascii char
                if(!padding)// do not add to plain text if padding 
                s = s+letter;
            }
            binary[i] =s;
        }
        return binary;

    }

    public static String xorStrings(String m, String k){
        char[] Cm = m.toCharArray();
        char[] Ck = k.toCharArray();
        for(int i=0; i<m.length();i++){
            Cm[i] = xor(Cm[i], Ck[i]);
        }
        String out = new String(Cm);
        return out;
    }

    public static char xor(char a, char b){
        char xbit = '0';
        if(a != b){
            xbit = '1';
        }
        return xbit;
    } 

    public static int binToDec(String bin){
        int decimal=Integer.parseInt(bin,2);  
        return decimal;
    }


    public static String hexToBin(String hex){
        String bin = "";
        String binFragment = "";
        int iHex;
        hex = hex.trim();
        hex = hex.replaceFirst("0x", "");
    
        for(int i = 0; i < hex.length(); i++){
            iHex = Integer.parseInt(""+hex.charAt(i),16);
            binFragment = Integer.toBinaryString(iHex);
    
            while(binFragment.length() < 4){
                binFragment = "0" + binFragment;
            }
            bin += binFragment;
            
        }
        while(bin.length()<8)
        {
                bin = "0" + bin;
        }
        return bin;
    }

    public static String binToHex(String bin){

        int number = Integer.parseInt(bin,2);
        String strHexadecimal = Integer.toHexString(number);
        return strHexadecimal;
    }

    public static String LineHexTobin(String hex){
        String[] split = hex.split(" ");
        String out = "";
        for(int i=0;i<split.length;i++){
            split[i] = hexToBin(split[i]);
            out = out + split[i];
        }
        return out;
    }

    static String[] reverseArray(String[] a)
    {
        String[] b = new String[a.length];
        int j = a.length;
        for (int i = 0; i < a.length; i++) {
            b[j - 1] = a[i];
            j = j - 1;
        }
        return b;
    } 

    public static void saveEncodedToHexFile(String[] encoded, String encodedFileNAme ){
        // converting binary output to hex and saving to file
        String[] encodedHex = new String[encoded.length];
        for(int i=0;i<encoded.length;i++){
            String[] bytes= encoded[i].split("(?<=\\G.{" + 8 + "})");
            encodedHex[i] = "";
            for(int j=0;j<bytes.length;j++){
              encodedHex[i] = encodedHex[i] + binToHex(bytes[j]) + " "; 
          }
          IO.writeRow(encodedHex[i], encodedFileNAme);
        }  
    }

    public static String[] readEncodedHexFile(String encodedFileNAme){
        String[] encodedHexFile = IO.readFile(encodedFileNAme);

        String[] bin = new String[encodedHexFile.length];
        for(int i=0;i<encodedHexFile.length;i++){
            bin[i] = LineHexTobin(encodedHexFile[i]);
        }
        return bin;

    }

   
}
