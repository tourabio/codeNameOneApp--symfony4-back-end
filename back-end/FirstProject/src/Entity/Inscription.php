<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Inscription
 *
 * @ORM\Table(name="inscription", indexes={@ORM\Index(name="id_etud", columns={"id_etud"}), @ORM\Index(name="id_ex", columns={"id_ex"})})
 * @ORM\Entity
 */
class Inscription
{
    /**
     * @var int
     *
     * @ORM\Column(name="id_inscri", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idInscri;

    /**
     * @var \DateTime
     *
     * @ORM\Column(name="date_in", type="date", nullable=false)
     */
    private $dateIn;

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
     *   @ORM\JoinColumn(name="id_ex", referencedColumnName="id_examen")
     * })
     */
    private $idEx;

    public function getIdInscri(): ?int
    {
        return $this->idInscri;
    }

    public function getDateIn(): ?\DateTimeInterface
    {
        return $this->dateIn;
    }

    public function setDateIn(\DateTimeInterface $dateIn): self
    {
        $this->dateIn = $dateIn;

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

    public function getIdEx(): ?Examen
    {
        return $this->idEx;
    }

    public function setIdEx(?Examen $idEx): self
    {
        $this->idEx = $idEx;

        return $this;
    }


}
