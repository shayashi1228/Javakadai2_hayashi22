package DBLesson01;
import java.util.ArrayList;
import java.util.Scanner;

public class DBLesson01{
	@SuppressWarnings("resource")
	public static void main(String[] args){
		ArrayList<Word> words = new ArrayList<>();

		System.out.println("英単語と日本語をスペースで区切って入力して下さい。");
		Scanner sc = new Scanner(System.in);
		String input = sc.nextLine();
		int index = 0;
		try{
			while(!input.equals("e")){
				String[] tmp = new String[2];
				tmp = input.split(" ");
				Word wd = new Word(tmp[0], tmp[1]);
				words.add(wd);
				index++;
				System.out.println("次の英単語と日本語を入力して下さい。\"e\"で終了します。");
				input = sc.nextLine();
			}
		}catch(ArrayIndexOutOfBoundsException e){
			System.out.println("登録制限を超えました。登録済みのデータは以下になります。");
		}
		WordDAO dao = new WordDAO();
		int saveCount = dao.registWords(words);
		System.out.println(saveCount + "件、登録しました。");
	}
}