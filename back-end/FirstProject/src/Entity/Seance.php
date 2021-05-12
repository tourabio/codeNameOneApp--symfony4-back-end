<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Seance
 *
 * @ORM\Table(name="seance", indexes={@ORM\Index(name="ID_User", columns={"ID_User"}), @ORM\Index(name="ID_Class", columns={"ID_Class"})})
 * @ORM\Entity
 */
class Seance
{
    /**
     * @var int
     *
     * @ORM\Column(name="ID_Seance", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idSeance;

    /**
     * @var string
     *
     * @ORM\Column(name="Subject", type="string", length=30, nullable=false)
     */
    private $subject;

    /**
     * @var string
     *
     * @ORM\Column(name="Length", type="string", length=20, nullable=false)
     */
    private $length;

    /**
     * @var string
     *
     * @ORM\Column(name="Date", type="string", length=20, nullable=false)
     */
    private $date;

    /**
     * @var \Classe
     *
     * @ORM\ManyToOne(targetEntity="Classe")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="ID_Class", referencedColumnName="ID_Class")
     * })
     */
    private $idClass;

    /**
     * @var \User
     *
     * @ORM\ManyToOne(targetEntity="User")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="ID_User", referencedColumnName="ID_User")
     * })
     */
    private $idUser;

    public function getIdSeance(): ?int
    {
        return $this->idSeance;
    }

    public function getSubject(): ?string
    {
        return $this->subject;
    }

    public function setSubject(string $subject): self
    {
        $this->subject = $subject;

        return $this;
    }

    public function getLength(): ?string
    {
        return $this->length;
    }

    public function setLength(string $length): self
    {
        $this->length = $length;

        return $this;
    }

    public function getDate(): ?string
    {
        return $this->date;
    }

    public function setDate(string $date): self
    {
        $this->date = $date;

        return $this;
    }

    public function getIdClass(): ?Classe
    {
        return $this->idClass;
    }

    public function setIdClass(?Classe $idClass): self
    {
        $this->idClass = $idClass;

        return $this;
    }

    public function getIdUser(): ?User
    {
        return $this->idUser;
    }

    public function setIdUser(?User $idUser): self
    {
        $this->idUser = $idUser;

        return $this;
    }


}
