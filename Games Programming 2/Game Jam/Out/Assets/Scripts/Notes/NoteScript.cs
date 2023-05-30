using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class NoteScript : MonoBehaviour
{
    public GameController gameController;
    public SpriteRenderer spriteRenderer;

    // Start is called before the first frame update
    void Start()
    {
        gameController = GameObject.Find("Game Controller").GetComponent<GameController>();
    }

    void FixedUpdate()
    {

    }

    private void OnTriggerEnter(Collider other)
    {
        if (other.gameObject.tag == "Player")
        {
            gameController.setNoteUIActive(true);
            gameController.NoteUI.GetComponent<NoteUI>().Set_Note_Visual(spriteRenderer.sprite.name);
        }
    }

    private void OnTriggerExit(Collider other)
    {
        if (other.gameObject.tag == "Player")
        {
            gameController.setNoteUIActive(false);
        }   
    }
}