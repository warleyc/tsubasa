/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { TsubasaTestModule } from '../../../test.module';
import { MGuerillaQuestStageRewardDeleteDialogComponent } from 'app/entities/m-guerilla-quest-stage-reward/m-guerilla-quest-stage-reward-delete-dialog.component';
import { MGuerillaQuestStageRewardService } from 'app/entities/m-guerilla-quest-stage-reward/m-guerilla-quest-stage-reward.service';

describe('Component Tests', () => {
  describe('MGuerillaQuestStageReward Management Delete Component', () => {
    let comp: MGuerillaQuestStageRewardDeleteDialogComponent;
    let fixture: ComponentFixture<MGuerillaQuestStageRewardDeleteDialogComponent>;
    let service: MGuerillaQuestStageRewardService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MGuerillaQuestStageRewardDeleteDialogComponent]
      })
        .overrideTemplate(MGuerillaQuestStageRewardDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MGuerillaQuestStageRewardDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MGuerillaQuestStageRewardService);
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
