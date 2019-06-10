/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { TsubasaTestModule } from '../../../test.module';
import { MCardRarityDeleteDialogComponent } from 'app/entities/m-card-rarity/m-card-rarity-delete-dialog.component';
import { MCardRarityService } from 'app/entities/m-card-rarity/m-card-rarity.service';

describe('Component Tests', () => {
  describe('MCardRarity Management Delete Component', () => {
    let comp: MCardRarityDeleteDialogComponent;
    let fixture: ComponentFixture<MCardRarityDeleteDialogComponent>;
    let service: MCardRarityService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MCardRarityDeleteDialogComponent]
      })
        .overrideTemplate(MCardRarityDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MCardRarityDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MCardRarityService);
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
