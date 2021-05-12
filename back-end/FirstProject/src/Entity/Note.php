<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Note
 *
 * @ORM\Table(name="note", indexes={@ORM\Index(name="id_examen", columns={"id_examen"}), @ORM\Index(name="id_etud", columns={"id_etud"})})
 * @ORM\Entity
 */
class Note
{
    /**
     * @var int
     *
     * @ORM\Column(name="id_note", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idNote;

    /**
     * @var string
     *
     * @ORM\Column(name="nom_matiere", type="string", length=200, nullable=false)
     */
    private $nomMatiere;

    /**
     * @var int
     *
     * @ORM\Column(name="note_cc", type="integer", nullable=false)
     */
    private $noteCc;

    /**
     * @var int
     *
     * @ORM\Column(name="note_ex", type="integer", nullable=false)
     */
    private $noteEx;

    /**
     * @var string
     *
     * @ORM\Column(name="nom", type="string", length=50, nullable=false)
     */
    private $nom;

    /**
     * @var string
     *
     * @ORM\Column(name="prenom", type="string", length=50, nullable=false)
     */
    private $prenom;

    /**
     * @var \User
     *
     * @ORM\ManyToOne(targetEntity="User")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="id_etud", referencedColumnName="ID_User")
     * })
     */
    private $idEtud;

    /**
     * @var \Examen
     *
     * @ORM\ManyToOne(targetEntity="Examen")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="id_examen", referencedColumnName="id_examen")
     * })
     */
    private $idExamen;

    public function getIdNote(): ?int
    {
        return $this->idNote;
    }

    public function getNomMatiere(): ?string
    {
        return $this->nomMatiere;
    }

    public function setNomMatiere(string $nomMatiere): self
    {
        $this->nomMatiere = $nomMatiere;

        return $this;
    }

    public function getNoteCc(): ?int
    {
        return $this->noteCc;
    }

    public function setNoteCc(int $noteCc): self
    {
        $this->noteCc = $noteCc;

        return $this;
    }

    public function getNoteEx(): ?int
    {
        return $this->noteEx;
    }

    public function setNoteEx(int $noteEx): self
    {
        $this->noteEx = $noteEx;

        return $this;
    }

    public function getNom(): ?string
    {
        return $this->nom;
    }

    public function setNom(string $nom): self
    {
        $this->nom = $nom;

        return $this;
    }

    public function getPrenom(): ?string
    {
        return $this->prenom;
    }

    public function setPrenom(string $prenom): self
    {
        $this->prenom = $prenom;

        return $this;
    }

    public function getIdEtud(): ?User
    {
        return $this->idEtud;
    }

    public function setIdEtud(?User $idEtud): self
    {
        $this->idEtud = $idEtud;

        return $this;
    }

    public function getIdExamen(): ?Examen
    {
        return $this->idExamen;
    }

    public function setIdExamen(?Examen $idExamen): self
    {
        $this->idExamen = $idExamen;

        return $this;
    }


}
