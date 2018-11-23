package Cipher;

import java.util.Scanner;

/**
Caesar Cipher is a simple way of message encoding: 
it moves the constants of each letter in the message according to the alphabet. 
For example, if ķ is equal to 3, in the encoded message, 
each letter will move forward by 3 bits: 
'a' will be replaced with 'd', 
'b' will be replaced with 'f', and so on. 
The end of the alphabet will be rolled back to the beginning of the alphabet. 
Thus, 'w' will be replaced by 'z' and 'x' will be replaced by 'a'. 
When decoding a message, each letter moves the same number of bits in the opposite direction.
**/

/**
 * Caesar Cipher
 * 
 * @author Huyuxi
 * @date 2018-11-23
 */
public class CaesarCipher {
	/**
	 * 
	 * @param message
	 *            string
	 * @param shift
	 * @return message string after encoding
	 * 
	 */
	public static String encode(String message, int shift) {
		String encoded = "";
		for (int i = 0; i < message.length(); i++) {
			int current = message.charAt(i); // using char to shift characters because ASCII is in-order Latin alphabet
			if (current == 32) {
				// the ASCII of space is 32
				encoded += " ";
				continue;
			} else if (current >= 65 && current <= 90) {
				// 'A' to 'Z'
				int numAlphabet = message.charAt(i);
				if (shift + numAlphabet > 90) {
					// roll
					int j = 90 - numAlphabet;
					// replace
					char nextKey = (char) (65 + (shift - j - 1));
					encoded += nextKey;
				} else {
					// replace
					char nextKey = (char) (current + shift);
					encoded += nextKey;
				}
			} else if (current >= 97 && current <= 122) {
				// 'a' to 'z'
				int numAlphabet = message.charAt(i);
				if (shift + numAlphabet > 122) {
					// roll
					int j = 122 - numAlphabet;
					// replace
					char nextKey = (char) (97 + (shift - j - 1));
					encoded += nextKey;
				} else {
					// replace
					char nextKey = (char) (current + shift);
					encoded += nextKey;
				}
			}
		}
		return encoded;
	}

	/**
	 * 
	 * @param message
	 *            cipher text
	 * @param shift
	 * @return string after decoding
	 * 
	 */
	public static String decode(String message, int shift) {
		String decoded = "";
		for (int i = 0; i < message.length(); i++) {
			int current = message.charAt(i);
			if (current == 32) {
				decoded += " ";
				continue;
			} else if (current >= 65 && current <= 90) {
				int numAlphabet = message.charAt(i);
				if (numAlphabet - shift < 65) {
					int j = numAlphabet - 65;
					char nextKey = (char) (90 - (shift - j - 1));
					decoded += nextKey;
				} else {
					char nextKey = (char) (current - shift);
					decoded += nextKey;
				}
			} else if (current >= 97 && current <= 122) {
				int numAlphabet = message.charAt(i);
				if (numAlphabet - shift < 97) {
					int j = numAlphabet - 97;
					char nextKey = (char) (122 - (shift - j - 1));
					decoded += nextKey;
				} else {
					char nextKey = (char) (current - shift);
					decoded += nextKey;
				}
			}
		}
		return decoded;
	}

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("Please enter the message (Latin Alphabet)");
		String message = input.nextLine();
		System.out.println(message);
		System.out.println("Please enter the shift number");
		int shift = input.nextInt() % 26;
		System.out.println("(E)ncode or (D)ecode ?");
		char choice = input.next().charAt(0);
		if (choice == 'E' || choice == 'e')
			System.out.println("ENCODED MESSAGE IS \n" + encode(message, shift)); // send our function to handle
		if (choice == 'D' || choice == 'd')
			System.out.println("DECODED MESSAGE IS \n" + decode(message, shift));
	}

}