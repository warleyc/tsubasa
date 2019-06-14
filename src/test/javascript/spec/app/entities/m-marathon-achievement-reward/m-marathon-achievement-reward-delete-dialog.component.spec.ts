/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { TsubasaTestModule } from '../../../test.module';
import { MMarathonAchievementRewardDeleteDialogComponent } from 'app/entities/m-marathon-achievement-reward/m-marathon-achievement-reward-delete-dialog.component';
import { MMarathonAchievementRewardService } from 'app/entities/m-marathon-achievement-reward/m-marathon-achievement-reward.service';

describe('Component Tests', () => {
  describe('MMarathonAchievementReward Management Delete Component', () => {
    let comp: MMarathonAchievementRewardDeleteDialogComponent;
    let fixture: ComponentFixture<MMarathonAchievementRewardDeleteDialogComponent>;
    let service: MMarathonAchievementRewardService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MMarathonAchievementRewardDeleteDialogComponent]
      })
        .overrideTemplate(MMarathonAchievementRewardDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MMarathonAchievementRewardDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MMarathonAchievementRewardService);
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
