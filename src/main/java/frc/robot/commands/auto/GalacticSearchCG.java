package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class GalacticSearchCG extends CommandGroup {

    public GalacticSearchCG(String path, String color) {
        if (path == "A" && color == "Blue") {
            addSequential(new Drive(29 * Math.PI / 180, 0.4, 0), 3);
            addSequential(new GalacticSearch(path, color));
        }
    }
}
