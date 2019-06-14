/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { TsubasaTestModule } from '../../../test.module';
import { MLoginBonusIncentiveDeleteDialogComponent } from 'app/entities/m-login-bonus-incentive/m-login-bonus-incentive-delete-dialog.component';
import { MLoginBonusIncentiveService } from 'app/entities/m-login-bonus-incentive/m-login-bonus-incentive.service';

describe('Component Tests', () => {
  describe('MLoginBonusIncentive Management Delete Component', () => {
    let comp: MLoginBonusIncentiveDeleteDialogComponent;
    let fixture: ComponentFixture<MLoginBonusIncentiveDeleteDialogComponent>;
    let service: MLoginBonusIncentiveService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MLoginBonusIncentiveDeleteDialogComponent]
      })
        .overrideTemplate(MLoginBonusIncentiveDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MLoginBonusIncentiveDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MLoginBonusIncentiveService);
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
