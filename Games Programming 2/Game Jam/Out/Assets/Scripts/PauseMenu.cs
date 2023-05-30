using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class PauseMenu : MonoBehaviour
{
    public GameController gameController;

    public Text MenuTitle;
    public Text TimeDisplay;
    public GameObject ResumeButton;

    private void Start()
    {
        gameController = GameObject.Find("Game Controller").GetComponent<GameController>();
    }

    public void clickFunction(string buttonClicked)
    {
        switch (buttonClicked)
        {
            case "Resume":
                ResumeButtonFunction();
                break;
        }
    }

    void ResumeButtonFunction()
    {
        switch (gameController.isGameOver())
        {
            case true:
                gameController.RestartGame();
                break;

            case false:
                gameController.playTimer.TimerStart();
                gameObject.SetActive(false);
                break;
        }
    }
}
