package Lab3;

public class HelpCommand implements Command {
    private CommandInvoker commandInvoker;

    public HelpCommand(CommandInvoker commandInvoker) {
        this.commandInvoker = commandInvoker;
    }

    @Override
    public void execute() {
        commandInvoker.displayHelpMenu();
    }

    @Override
    public String getDescription() {
        return "Help: Display help menu";
    }
}
