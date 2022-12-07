package de.hhn.it.devtools.components.textBasedLabyrinth.textbasedlabyrinth;

public class SpecialTrapTriggerItem extends Item {


    /**
     * Every item has an item id, a name and an information.
     *
     * @param id ID of the item.
     * @param name name of the item.
     * @param info description of the item.
     */
    public SpecialTrapTriggerItem(int id, String name, String info) {
        super(id, name, info);
    }

    @Override
    public boolean getIsTrapActivator() {
        return true;
    }
}
