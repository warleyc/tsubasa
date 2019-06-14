/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { TsubasaTestModule } from '../../../test.module';
import { MPvpWaveDeleteDialogComponent } from 'app/entities/m-pvp-wave/m-pvp-wave-delete-dialog.component';
import { MPvpWaveService } from 'app/entities/m-pvp-wave/m-pvp-wave.service';

describe('Component Tests', () => {
  describe('MPvpWave Management Delete Component', () => {
    let comp: MPvpWaveDeleteDialogComponent;
    let fixture: ComponentFixture<MPvpWaveDeleteDialogComponent>;
    let service: MPvpWaveService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MPvpWaveDeleteDialogComponent]
      })
        .overrideTemplate(MPvpWaveDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MPvpWaveDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MPvpWaveService);
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
