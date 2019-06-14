/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { TsubasaTestModule } from '../../../test.module';
import { MWeeklyQuestTsubasaPointRewardDeleteDialogComponent } from 'app/entities/m-weekly-quest-tsubasa-point-reward/m-weekly-quest-tsubasa-point-reward-delete-dialog.component';
import { MWeeklyQuestTsubasaPointRewardService } from 'app/entities/m-weekly-quest-tsubasa-point-reward/m-weekly-quest-tsubasa-point-reward.service';

describe('Component Tests', () => {
  describe('MWeeklyQuestTsubasaPointReward Management Delete Component', () => {
    let comp: MWeeklyQuestTsubasaPointRewardDeleteDialogComponent;
    let fixture: ComponentFixture<MWeeklyQuestTsubasaPointRewardDeleteDialogComponent>;
    let service: MWeeklyQuestTsubasaPointRewardService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MWeeklyQuestTsubasaPointRewardDeleteDialogComponent]
      })
        .overrideTemplate(MWeeklyQuestTsubasaPointRewardDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MWeeklyQuestTsubasaPointRewardDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MWeeklyQuestTsubasaPointRewardService);
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
