using System.Collections;
using System.Collections.Generic;
using System;
using UnityEngine;
using UnityEngine.UI;
using UnityEngine.SceneManagement;


public class GameController : MonoBehaviour
{
    public Player Player;
    public GameObject PinPadUI;
    public PauseMenu PauseMenuUI;
    public Timer playTimer;

    private SceneManager SceneManager;

    [SerializeField]
    private bool gameOver = false;
    // Start is called before the first frame update
    void Start()
    {
        Player = GameObject.Find("Player").GetComponent<Player>();
    }

    void Update()
    {
        if(PinPadUI.activeSelf)
        {
            PinPadUI.GetComponent<PinPadButton>().Door.isStillUnlocking(true);
            Player.gameObject.GetComponent<Player>().enabled = false;
        }
        else
        {
            Player.gameObject.GetComponent<Player>().enabled = true;
        }

        if (Input.GetKeyDown(KeyCode.Escape))
        {
            playTimer.TImerStop();
            PauseMenuUI.TimeDisplay.text = "TIME\n" + Math.Round(playTimer.showTime(), 1) + "secs";
            PauseMenuUI.gameObject.SetActive(true);
        }
    }

    public void setPinPadActive(bool isActive) 
    {
        PinPadUI.SetActive(isActive); 
    }

    // Has the game ended???
    public bool isGameOver() { return gameOver; }

    public void GameOverProcedure()
    {
        gameOver = true;

        PauseMenuUI.gameObject.SetActive(true);
        PauseMenuUI.ResumeButton.GetComponentInChildren<Text>().text = "Click Here to Restart";
        PauseMenuUI.MenuTitle.text = "Game Over";
    }

    // Reloads entire scene, restarting game.
    public void RestartGame()
    {
        SceneManager.LoadScene("SampleScene");
    }
}
