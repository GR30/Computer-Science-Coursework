using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class DoorScript : MonoBehaviour
{
    GameController gameController;

    Animator anim;
    private string correctPin = "2193";
    private bool locked = true;

    // Start is called before the first frame update
    void Start()
    {
        gameController = GameObject.Find("Game Controller").GetComponent<GameController>();
        anim = GetComponent<Animator>();
    }
    
     void OnTriggerEnter(Collider other)
    {
        Player p = other.GetComponent<Player>();

        if (p != null) {
            if (locked) { StartCoroutine(LockedDoorCoroutine()); }
            else { anim.SetTrigger("openDoor"); }
        }
        
    }
    
    void OnTriggerExit(Collider other)
    {  
        anim.enabled = true;
    }
    void pauseAnimationEvent()
    {
        anim.enabled = false;
    }

    public string getPIN() { return correctPin; }
    public void setLock(bool isLocked) { locked = isLocked; }
    private bool getLock() { return locked; }
    IEnumerator LockedDoorCoroutine()
    {
        yield return new WaitUntil(() => getLock() == false);
        anim.SetTrigger("openDoor");
    }
}
