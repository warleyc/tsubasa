/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { TsubasaTestModule } from '../../../test.module';
import { MLoginBonusRoundDeleteDialogComponent } from 'app/entities/m-login-bonus-round/m-login-bonus-round-delete-dialog.component';
import { MLoginBonusRoundService } from 'app/entities/m-login-bonus-round/m-login-bonus-round.service';

describe('Component Tests', () => {
  describe('MLoginBonusRound Management Delete Component', () => {
    let comp: MLoginBonusRoundDeleteDialogComponent;
    let fixture: ComponentFixture<MLoginBonusRoundDeleteDialogComponent>;
    let service: MLoginBonusRoundService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MLoginBonusRoundDeleteDialogComponent]
      })
        .overrideTemplate(MLoginBonusRoundDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MLoginBonusRoundDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MLoginBonusRoundService);
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
