import java.io.FileNotFoundException;
import java.util.ArrayList;


/**
 * 
 * @author Anton Belev 1103816b
 * Known plain text attack - brute force algorithm
 */
public class KPT {
	
	private static String plainTextFristBlock = "0x446f";
	private static String cipherTextFirstBlock = "0xd1b2";
	private static String cipherTextFirstBlockStringDecrypted = "";
	private static final String C1_TXT = "c1.txt";


	public static void main(String[] args) throws FileNotFoundException {
		ArrayList<String> cipherBlocks = Utils.getCipherBlocks(C1_TXT);
		for (int key = 0; key < Math.pow(2, 16); key++){
			cipherTextFirstBlockStringDecrypted = decrypt(Utils.intToHexString(key), cipherTextFirstBlock);
			if (cipherTextFirstBlockStringDecrypted.equals(plainTextFristBlock)){
				System.out.println("Key found. Decimal value - " + key 
						+ " Hex value - " + Utils.intToHexString(key));
				ArrayList<String> dblocks = Utils.decrypt(cipherBlocks, Utils.intToHexString(key));
				String text = Utils.block2Text(dblocks);
				System.out.println("Decrypted text:\n" + text);
				break;
			}
		}
	}
	
	private static String decrypt(String hexString, String cipherBlock){
		int	key = Hex16.convert(hexString);
		int	c = Hex16.convert(cipherBlock);
		int	p = Coder.decrypt(key, c);
		String	out = String.format("0x%04x", p);
		return out;
	}
}
