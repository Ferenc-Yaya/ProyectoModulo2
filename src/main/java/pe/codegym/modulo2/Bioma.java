package pe.codegym.modulo2;

import java.util.stream.Collectors;

public class Bioma implements Runnable{
    PosicionStrategy posicionStrategy;
    Thread hiloBioma;

    public Bioma(PosicionStrategy posicionStrategy) {
        this.posicionStrategy=posicionStrategy;
        this.hiloBioma=new Thread(this);
        this.hiloBioma.start();
    }

    @Override
    public void run() {
        while(!this.hiloBioma.isInterrupted()){

            String resultadosAnimales = Animal.obtenerAnimales().stream()
                    .collect(Collectors.toMap(
                            a -> a.getClass().getSimpleName(),
                            a -> a.getEmoji() + "(" + String.format("%.2f", a.getVidaAnimal())+")",
                            (emoji1, emoji2) -> emoji1 +","+ emoji2
                    ))
                    .entrySet().stream()
                    .map(e -> e.getKey() + "->" + e.getValue())
                    .collect(Collectors.joining("\n"));

            String resultadosPlantas = Planta.obtenerPlantas().stream()
                    .collect(Collectors.toMap(
                            a -> a.getClass().getSimpleName(),
                            Planta::getEmoji,
                            (emoji1, emoji2) -> emoji1 +","+ emoji2
                    ))
                    .entrySet().stream()
                    .map(e -> e.getKey() + "->" + e.getValue())
                    .collect(Collectors.joining("\n"));

            this.posicionStrategy.resultadosGenerales(resultadosAnimales+"\n"+resultadosPlantas);

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}
