<?php

namespace App\Controller;

use App\Entity\Depense;
use App\Form\DepenseType;
use App\Repository\DepenseRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

/**
 * @Route("/depense")
 */
class DepenseController extends AbstractController
{
    /**
     * @Route("/", name="depense_index", methods={"GET"})
     */
    public function index(DepenseRepository $depenseRepository): Response
    {
        return $this->render('depense/index.html.twig', [
            'depenses' => $depenseRepository->findAll(),
        ]);
    }

    /**
     * @Route("/new", name="depense_new", methods={"GET","POST"})
     */
    public function new(Request $request): Response
    {
        $depense = new Depense();
        $form = $this->createForm(DepenseType::class, $depense);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->persist($depense);
            $entityManager->flush();

            return $this->redirectToRoute('depense_index');
        }

        return $this->render('depense/new.html.twig', [
            'depense' => $depense,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{id}", name="depense_show", methods={"GET"})
     */
    public function show(Depense $depense): Response
    {
        return $this->render('depense/show.html.twig', [
            'depense' => $depense,
        ]);
    }

    /**
     * @Route("/{id}/edit", name="depense_edit", methods={"GET","POST"})
     */
    public function edit(Request $request, Depense $depense): Response
    {
        $form = $this->createForm(DepenseType::class, $depense);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $this->getDoctrine()->getManager()->flush();

            return $this->redirectToRoute('depense_index');
        }

        return $this->render('depense/edit.html.twig', [
            'depense' => $depense,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{id}", name="depense_delete", methods={"POST"})
     */
    public function delete(Request $request, Depense $depense): Response
    {
        if ($this->isCsrfTokenValid('delete'.$depense->getId(), $request->request->get('_token'))) {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->remove($depense);
            $entityManager->flush();
        }

        return $this->redirectToRoute('depense_index');
    }
}
