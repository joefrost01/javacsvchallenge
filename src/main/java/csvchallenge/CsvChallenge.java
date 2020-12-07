package csvchallenge;

public class CsvChallenge {

    public static void main(String[] args) {
        try {
            // generate 1000000 big_file.tsv
            if (args[0].toLowerCase().equals("generate") && args.length == 3) {
                FileGenerator gen = new FileGenerator();
                gen.generateFile(args[2], Integer.parseInt(args[1]));
            } else {
                // process big_file.tsv output_file.csv
                FileProcessorStreams.processFile(args[1], args[2]);

            }
        } catch (Exception e) {
            System.out.println("Usage is either: generate <size in lines> <output file> or process <source file> <output file>");
        }
    }

}
