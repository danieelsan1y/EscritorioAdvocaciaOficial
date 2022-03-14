package tarefa;


public class ThreadExemplo {
	public static void main(String[] args) {
		ThreadAudiencia t01 = new ThreadAudiencia("Thread01", "0001");
		//ThreadAudiencia t02 = new ThreadAudiencia("Thread02", 10);
		t01.setPriority(Thread.NORM_PRIORITY - 1);
		//t02.setPriority(Thread.NORM_PRIORITY + 5);
		t01.start();
		//t02.start();

		try {
			t01.join();

			//t02.join();
		} catch (InterruptedException e) {
		}

		//System.out.println("Thread01 contou = " + t01.contador);
		//System.out.println("Thread02 contou = " + t02.contador);
	}
}