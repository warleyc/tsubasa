/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { TsubasaTestModule } from '../../../test.module';
import { MGuildEffectDeleteDialogComponent } from 'app/entities/m-guild-effect/m-guild-effect-delete-dialog.component';
import { MGuildEffectService } from 'app/entities/m-guild-effect/m-guild-effect.service';

describe('Component Tests', () => {
  describe('MGuildEffect Management Delete Component', () => {
    let comp: MGuildEffectDeleteDialogComponent;
    let fixture: ComponentFixture<MGuildEffectDeleteDialogComponent>;
    let service: MGuildEffectService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MGuildEffectDeleteDialogComponent]
      })
        .overrideTemplate(MGuildEffectDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MGuildEffectDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MGuildEffectService);
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
