package net.fabricmc.transmutation_patch;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class transmutation_patch implements ModInitializer {

    public static final Logger LOGGER = LoggerFactory.getLogger("transmutation_patch");

    @Override
    public void onInitialize() {
        LOGGER.info("Started transmutation patch");
    }
}