/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { TsubasaTestModule } from '../../../test.module';
import { MTimeattackQuestStageRewardDeleteDialogComponent } from 'app/entities/m-timeattack-quest-stage-reward/m-timeattack-quest-stage-reward-delete-dialog.component';
import { MTimeattackQuestStageRewardService } from 'app/entities/m-timeattack-quest-stage-reward/m-timeattack-quest-stage-reward.service';

describe('Component Tests', () => {
  describe('MTimeattackQuestStageReward Management Delete Component', () => {
    let comp: MTimeattackQuestStageRewardDeleteDialogComponent;
    let fixture: ComponentFixture<MTimeattackQuestStageRewardDeleteDialogComponent>;
    let service: MTimeattackQuestStageRewardService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MTimeattackQuestStageRewardDeleteDialogComponent]
      })
        .overrideTemplate(MTimeattackQuestStageRewardDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MTimeattackQuestStageRewardDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MTimeattackQuestStageRewardService);
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
