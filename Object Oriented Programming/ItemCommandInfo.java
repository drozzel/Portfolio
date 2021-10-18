package com.company;

public class ItemCommandInfo {
    private String commandName;
    private String commandType;
    private String commandMsg;
    private String returnItem;

    /**This is the default constructor for the ItemCommandInfo class.
      */
    public ItemCommandInfo(){ }

    /**This is the parametrized constructor for the ItemCommandInfo class.
     *
     * @param commandName a String representing the name of the command.
     * @param commandType a String representing the type of the command.
     * @param commandMsg a String representing the message of the command.
     */
    ItemCommandInfo(String commandName, String commandType, String commandMsg){
        this.commandMsg = commandMsg;
        this.commandName = commandName;
        this.commandType = commandType;
    }

    /**This method sets the message for the command.
     *
     * @param commandMsg a String representing the message of the command.
     */
    public void setCommandMsg(String commandMsg) {
        this.commandMsg = commandMsg;
    }

    /**This method returns the message for the command.
     *
     * @return A String representing the message of the command.
     */
    public String getCommandMsg() {
        return commandMsg;
    }

    /**This method sets the name of the command.
     *
     * @param commandName a String representing the name of the command.
     */
    public void setCommandName(String commandName) {
        this.commandName = commandName;
    }

    /**This method returns the name of the command.
     *
     * @return a String representing the name of the command.
     */
    public String getCommandName() {
        return commandName;
    }

    /**This method sets the type of the command.
     *
     * @param commandType A String representing the type of the command.
     */
    public void setCommandType(String commandType) {
        this.commandType = commandType;
    }

    /**This method returns the type of the command.
     *
     * @return a String representing the type of the command.
     */
    public String getCommandType() {
        return commandType;
    }

    /**This method sets the name of the returnItem.
     *
     * @param returnItem a String representing the name of the returnItem.
     */
    public void setReturnItem(String returnItem) {
        this.returnItem = returnItem;
    }

    /**This method returns the name of the returnItem.
     *
     * @return a String representing the name of the returnItem.
     */
    public String getReturnItem() {
        return returnItem;
    }
}
