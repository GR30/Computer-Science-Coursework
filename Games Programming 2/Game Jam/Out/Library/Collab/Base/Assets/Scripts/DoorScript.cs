using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class DoorScript : MonoBehaviour
{
    GameController gameController;
    public PinPadButton pinPadButton;


    Animator anim;
    [SerializeField]
    private string correctPin = "2193";
    private bool locked = true;
    private bool coroutineStarted = false;
    private bool unlockAttemptInProgress = false;
    private int unlockAttempts = 0;

    // Start is called before the first frame update
    void Start()
    {
        gameController = GameObject.Find("Game Controller").GetComponent<GameController>();
        anim = GetComponent<Animator>();
        pinPadButton = gameController.PinPadUI.GetComponent<PinPadButton>();
    }

    void OnTriggerEnter(Collider other)
    {
        if (other.gameObject.tag == "Player")
        {
            if (locked && !coroutineStarted)
            {
                gameController.setPinPadUIActive(true);
                pinPadButton.setDoorReference(this);
                StartCoroutine(LockedDoorCoroutine());
            }
        }
    }

    void pauseAnimationEvent()
    {
        anim.enabled = false;
    }

    public string getPIN() { return correctPin; }
    public void setLock(bool isLocked) { locked = isLocked; }
    private bool getLock() { return locked; }
    public void isStillUnlocking(bool isUnlocking)
    {
        unlockAttemptInProgress = isUnlocking;
    }
    IEnumerator LockedDoorCoroutine()
    {
        coroutineStarted = true;
        unlockAttemptInProgress = true;
        yield return new WaitUntil(() => unlockAttemptInProgress == false);

        /*
         * WaitUntil door unlocking attempt is made.
         * If unlock attempt is successful, open the door.
         * 
         * If unsuccessful, count failed attempt (up to 3).
         * At max failed attempts, GAME OVER.
         */
        Debug.Log("Lock Status: " + getLock());
        if (getLock() == false)
        {
            anim.SetTrigger("OpenDoor");
        }
        else
        {
            unlockAttempts++;
            if (unlockAttempts > 2) { gameController.GameOverProcedure(); }
        }
        coroutineStarted = false;
    }
}