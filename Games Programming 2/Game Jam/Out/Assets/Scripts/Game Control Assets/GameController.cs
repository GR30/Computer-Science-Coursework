using System.Collections;
using System.Collections.Generic;
using System;
using UnityEngine;
using UnityEngine.UI;
using UnityEngine.SceneManagement;


public class GameController : MonoBehaviour
{
    public Player Player;
    public CameraScript Camera;
    public GameObject PinPadUI;
    //public PauseMenu PauseMenuUI;
    public GameObject NoteUI;
    public Timer playTimer;
    public Vector3 checkPointLocation;

    private SceneManager SceneManager;
    [SerializeField] private bool gameOver = false;

    // Start is called before the first frame update
    void Start()
    {
        Player = GameObject.Find("Player").GetComponent<Player>();
        Camera = GameObject.Find("Main Camera").GetComponent<CameraScript>();
        setCheckPointLocation(Player.transform.position);
    }

    void Update()
    {
        if(PinPadUI.activeSelf)
        {
            PinPadUI.GetComponent<PinPadButton>().Door.isStillUnlocking(true);
            Player.enabled = false;
            Camera.enabled = false;
        }
        else
        {
            Player.enabled = true;
            Camera.enabled = true;
        }

        if (Input.GetKeyDown(KeyCode.Escape))
        {
            playTimer.TImerStop();
            //PauseMenuUI.TimeDisplay.text = "TIME\n" + Math.Round(playTimer.showTime(), 1) + "secs";
            //PauseMenuUI.gameObject.SetActive(true);
        }
    }

    public void setPinPadUIActive(bool isActive) { PinPadUI.SetActive(isActive); }
    public void setNoteUIActive(bool isActive) { NoteUI.SetActive(isActive); }



    // Reloads entire scene, restarting game.
    public void RestartGame() { SceneManager.LoadScene("SampleScene"); }

    // Has the game ended???
    public bool isGameOver() { return gameOver; }
    public void GameOverProcedure()
    {
        gameOver = true;

        //PauseMenuUI.gameObject.SetActive(true);
        //PauseMenuUI.ResumeButton.GetComponentInChildren<Text>().text = "Click Here to Restart";
        //PauseMenuUI.MenuTitle.text = "Game Over";
    }
    
    public void setCheckPointLocation(Vector3 newCheckPoint)
    {
        checkPointLocation = newCheckPoint;
        Debug.Log(checkPointLocation);
    }
    public void toCheckPoint()
    {
        Player.gameObject.transform.position = checkPointLocation;
        Player.gameObject.SetActive(true);
    }
}
