using System;
using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class PinPadButton : MonoBehaviour
{
    public GameController gameController;

    public GameObject Pinpad;
    public GameObject Pin;
    public DoorScript Door; // should also include the correct pin

    private string function = "none";
    private Text txt;
    private int insertPin = 0;

    private int digitCount = 0;

    void Start()
    {
        txt = Pin.transform.Find("Text").GetComponent<Text>();
    }

    public void setDoorReference(DoorScript Door) { this.Door = Door; }

    // Start is called before the first frame update
    public void clickFunction()
    {
        if (function == "Number")
        {
            digitCount = 0;
            foreach (char num in txt.text) { digitCount++; }
            if (digitCount < 4) { txt.text += insertPin; }
        }
        else
        {
            if (function == "Enter")
            {
                if (Pin.GetComponentInChildren<Text>().text == Door.getPIN())
                {
                    this.transform.gameObject.SetActive(false);
                    gameController.checkPointLocation = gameController.Player.gameObject.transform.position;
                    Door.setLock(false);
                    Door.isStillUnlocking(false);
                }
                clearText();
            }
            else if (function == "Exit")
            {
                Door.isStillUnlocking(false);
                clearText();
                Pinpad.SetActive(false);
            }
        }
    }

    private void clearText()
    {
        txt.text = "";
    }

    public void addDigit(int num)
    {
        insertPin = num;
    }

    public void setFunction(string function)
    {
        this.function = function;
    }
}
