package playFairCipher;

import java.util.HashSet;

public class PlayFair implements CipherMethod {
	static HashSet<String> alpha;
	static int lastStoreColumn = -1;
	static int lastStoreRow = -1;
	static Node head, tail;
	static int arrLenCnt = 0;
	static {
		alpha = new HashSet<>();
		alpha.add("a");
		alpha.add("b");
		alpha.add("c");
		alpha.add("d");
		alpha.add("e");
		alpha.add("f");
		alpha.add("g");
		alpha.add("h");
		alpha.add("i");
		alpha.add("k");
		alpha.add("l");
		alpha.add("m");
		alpha.add("n");
		alpha.add("o");
		alpha.add("p");
		alpha.add("q");
		alpha.add("r");
		alpha.add("s");
		alpha.add("t");
		alpha.add("u");
		alpha.add("v");
		alpha.add("w");
		alpha.add("x");
		alpha.add("y");
		alpha.add("z");
	}

	public static void main(String[] args) {
		PlayFair pf = new PlayFair();
		pf.encode("test", "testkey");
	}

	@Override
	public String encode(String plainText, String key) {
		HashSet<String> hset = new HashSet<String>();

		key = key.replaceAll("\\s", "");
		
		String alphaMat[][] = new String[5][5];
		String keyArray[] = key.split("");


		int keyIter = 0;
		// insert the key into the 5*5 matrix
		for (int i = 0; i < 5; i++) {
			if (keyIter == keyArray.length) {
				break;
			}
			lastStoreRow += 1;
			lastStoreColumn = -1;
			for (int j = 0; j < 5; j++) {
				if (keyIter == keyArray.length) {
					break;
				}
				if (!hset.contains(keyArray[keyIter])) {
					alphaMat[i][j] = keyArray[keyIter];
					hset.add(keyArray[keyIter]);
					keyIter += 1;
				} else {
					alphaMat[i][j] = keyArray[keyIter + 1];
					keyIter += 2;
				}
				lastStoreColumn += 1;
			}
		}

		keyIter = 0;
		String[] fillArr = new String[25 - hset.size()];

		// filling remaining alphabets in a fillArray[]. This will later be
		// used to store in alphaMat[][].
		for (String i : alpha) {
			if (!hset.contains(i)) {
				fillArr[keyIter] = i;
				keyIter += 1;
			}
		}

		keyIter = 0;

		// filling the 5*5 array with remaining alphabets
		for (int i = lastStoreRow; i < 5;) {
			for (int j = lastStoreColumn + 1; j < 5; j++) {
				alphaMat[i][j] = fillArr[keyIter];
				keyIter += 1;
			}
			lastStoreRow += 1;
			break;
		}

		for (int i = lastStoreRow; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				alphaMat[i][j] = fillArr[keyIter];
				keyIter += 1;
			}
		}

		System.out.println("Final cipher array: ");
		System.out.println("[");
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				System.out.print(alphaMat[i][j] + ", ");
			}
			System.out.println();
		}
		System.out.print("]");
		System.out.println();
		System.out.println("Plain text: " + plainText);
		
		plainText = plainText.replaceAll("\\s", "");
		
		String[] plainTxtArray = plainText.split("");
		
		for(int i=0;i<plainTxtArray.length;i++) {
			System.out.print(plainTxtArray[i]+", ");
		}
		
		
		
		System.out.println("arrLenCnt = "+arrLenCnt);

		return null;
	}

	static void disp() {
		if (head != null) {
			Node tmp = head;
			while (tmp != null) {
				System.out.print(tmp.letter + ", ");
				tmp = tmp.next;
			}
		} else {
			System.out.println("plaintext list empty");
		}
	}

	@Override
	public String decode(String plainText) {
		// TODO Auto-generated method stub
		return null;
	}
}

class Node {
	Node next;
	String letter;

	Node(String letter) {
		this.letter = letter;
		next = null;
	}
}

interface CipherMethod {
	String encode(String plainText, String key);

	String decode(String cipherText);
}
