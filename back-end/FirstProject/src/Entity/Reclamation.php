<?php

namespace App\Entity;

use DateTimeInterface;
use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Serializer\Annotation\Groups;

/**
 * Reclamation
 *
 * @ORM\Table(name="reclamation", indexes={@ORM\Index(name="idUtilisateur", columns={"idUtilisateur"})})
 * @ORM\Entity
 * @ORM\Entity(repositoryClass="App\Repository\ReclamationRepository")
 */
class Reclamation
{
    /**
     * @var int
     *
     * @ORM\Column(name="id", type="bigint", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     * @Groups("rec:read")
     */
    private $id;

    /**
     * @var string
     *
     * @ORM\Column(name="sujet", type="string", length=50, nullable=false)
     * @Groups("rec:read")
     */
    private $sujet;

    /**
     * @var string
     *
     * @ORM\Column(name="comment", type="string", length=500, nullable=false)
     * @Groups("rec:read")
     */
    private $comment;

    /**
     * @var \DateTime
     *
     * @ORM\Column(name="date", type="date", nullable=false)
     * @Groups("rec:read")
     */
    private $date;

    /**
     * @var string
     *
     * @ORM\Column(name="etat", type="string", length=30, nullable=false)
     * @Groups("rec:read")
     */
    private $etat;

    /**
     * @var \User
     *
     * @ORM\ManyToOne(targetEntity="User")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="idUtilisateur", referencedColumnName="ID_User")
     * })
     */
    private $idutilisateur;







    private $dateFormatted;

    public function getDateFormatted(): ? string
    {
        return $this->dateFormatted;
    }

    public function setDateFormatted(string $dateFormatted): self
    {
        $this->dateFormatted = $dateFormatted;

        return $this;
    }




    public function getId(): ?string
    {
        return $this->id;
    }

    public function getSujet(): ?string
    {
        return $this->sujet;
    }

    public function setSujet(string $sujet): self
    {
        $this->sujet = $sujet;

        return $this;
    }

    public function getComment(): ?string
    {
        return $this->comment;
    }

    public function setComment(string $comment): self
    {
        $this->comment = $comment;

        return $this;
    }

    public function getDate(): ?\DateTimeInterface
    {
        return $this->date;
    }

    public function setDate(\DateTimeInterface $date): self
    {
        $this->date = $date;

        return $this;
    }

    public function getEtat(): ?string
    {
        return $this->etat;
    }

    public function setEtat(string $etat): self
    {
        $this->etat = $etat;

        return $this;
    }

    public function getIdutilisateur(): ? User
    {
        return $this->idutilisateur;
    }

    public function setIdutilisateur(?User $idutilisateur): self
    {
        $this->idutilisateur = $idutilisateur;

        return $this;
    }


}
