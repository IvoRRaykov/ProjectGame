
public class Test {
	public static void main(String[] args) {
		System.out.println("stignaaa");
		Player player = new Player(Color.BLUE);
		System.out.println("stignaaa1");
		Player otherPlayer = new Player(Color.RED);
		System.out.println("stignaaa2");
		GameUtil.giveZonesToPlayers(player, otherPlayer);
		System.out.println("stignaaa3");
		
		System.out.println(player.getAllQuestions());
	}
}
