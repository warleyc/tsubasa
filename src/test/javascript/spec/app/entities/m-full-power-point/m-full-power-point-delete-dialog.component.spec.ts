/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { TsubasaTestModule } from '../../../test.module';
import { MFullPowerPointDeleteDialogComponent } from 'app/entities/m-full-power-point/m-full-power-point-delete-dialog.component';
import { MFullPowerPointService } from 'app/entities/m-full-power-point/m-full-power-point.service';

describe('Component Tests', () => {
  describe('MFullPowerPoint Management Delete Component', () => {
    let comp: MFullPowerPointDeleteDialogComponent;
    let fixture: ComponentFixture<MFullPowerPointDeleteDialogComponent>;
    let service: MFullPowerPointService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MFullPowerPointDeleteDialogComponent]
      })
        .overrideTemplate(MFullPowerPointDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MFullPowerPointDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MFullPowerPointService);
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
