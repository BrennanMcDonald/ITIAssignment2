

class CircleTheDot{
	public static void main(String[] args) {
		StudentInfo i = new StudentInfo();
		i.display();
		int size = 7;
		if (args.length == 1) {
			try{
				size = Integer.parseInt(args[0]);
				if(size<4){
					System.out.println("Invalide argument, using default...");
					size = 9;
				}
			} catch(NumberFormatException e){
				System.out.println("Invalide argument, using default...");
			}
		}
		GameController game = new GameController(size);
		game.start();
	}
}