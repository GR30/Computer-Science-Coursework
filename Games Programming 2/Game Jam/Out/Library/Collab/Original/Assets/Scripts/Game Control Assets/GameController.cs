using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class GameController : MonoBehaviour
{
    public Player Player;
    public GameObject PinPadUI;
    public DoorScript targetDoor;
    public Timer playTimer;

    // Start is called before the first frame update
    void Start()
    {
        Player = GameObject.Find("Player").GetComponent<Player>();

        PinPadUI = GameObject.Find("Pinpad");

        playTimer = new Timer();
        playTimer.TimerStart();
    }

    // Update is called once per frame
    void Update()
    {
        
    }

    public void setPinPadActive(bool isActive) { PinPadUI.SetActive(isActive); }
    public void setTargetDoor(DoorScript targetDoor) { this.targetDoor = targetDoor; }
}
