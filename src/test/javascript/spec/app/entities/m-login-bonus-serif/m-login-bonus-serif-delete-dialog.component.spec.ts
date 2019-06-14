/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { TsubasaTestModule } from '../../../test.module';
import { MLoginBonusSerifDeleteDialogComponent } from 'app/entities/m-login-bonus-serif/m-login-bonus-serif-delete-dialog.component';
import { MLoginBonusSerifService } from 'app/entities/m-login-bonus-serif/m-login-bonus-serif.service';

describe('Component Tests', () => {
  describe('MLoginBonusSerif Management Delete Component', () => {
    let comp: MLoginBonusSerifDeleteDialogComponent;
    let fixture: ComponentFixture<MLoginBonusSerifDeleteDialogComponent>;
    let service: MLoginBonusSerifService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MLoginBonusSerifDeleteDialogComponent]
      })
        .overrideTemplate(MLoginBonusSerifDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MLoginBonusSerifDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MLoginBonusSerifService);
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
