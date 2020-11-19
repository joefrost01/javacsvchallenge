package csvchallenge;

public class CsvChallenge {

    public static void main(String[] args) {
        try {
            if (args[0].toLowerCase().equals("generate") && args.length == 3) {
                FileGenerator gen = new FileGenerator();
                gen.generateFile(args[2], Integer.parseInt(args[1]));
            } else {
                FileProcessorStreams proc = new FileProcessorStreams();
                TimeIt.printTime(() -> proc.processFile(args[1], args[2]));
            }
        } catch (Exception e) {
            System.out.println("Usage is either: generate <size in lines> <output file> or process <source file> <output file>");
        }
    }

}
