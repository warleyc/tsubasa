/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { TsubasaTestModule } from '../../../test.module';
import { MChallengeQuestStageDeleteDialogComponent } from 'app/entities/m-challenge-quest-stage/m-challenge-quest-stage-delete-dialog.component';
import { MChallengeQuestStageService } from 'app/entities/m-challenge-quest-stage/m-challenge-quest-stage.service';

describe('Component Tests', () => {
  describe('MChallengeQuestStage Management Delete Component', () => {
    let comp: MChallengeQuestStageDeleteDialogComponent;
    let fixture: ComponentFixture<MChallengeQuestStageDeleteDialogComponent>;
    let service: MChallengeQuestStageService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MChallengeQuestStageDeleteDialogComponent]
      })
        .overrideTemplate(MChallengeQuestStageDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MChallengeQuestStageDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MChallengeQuestStageService);
      mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
      mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
    });

    describe('confirmDelete', () => {
      it('Should call delete service on confirmDelete', inject(
        [],
        fakeAsync(() => {
          // GIVEN
          spyOn(service, 'delete').and.returnValue(of({}));

          // WHEN
          comp.confirmDelete(123);
          tick();

          // THEN
          expect(service.delete).toHaveBeenCalledWith(123);
          expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
          expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
        })
      ));
    });
  });
});
