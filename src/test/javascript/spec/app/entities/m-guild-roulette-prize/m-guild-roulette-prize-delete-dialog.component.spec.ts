/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { TsubasaTestModule } from '../../../test.module';
import { MGuildRoulettePrizeDeleteDialogComponent } from 'app/entities/m-guild-roulette-prize/m-guild-roulette-prize-delete-dialog.component';
import { MGuildRoulettePrizeService } from 'app/entities/m-guild-roulette-prize/m-guild-roulette-prize.service';

describe('Component Tests', () => {
  describe('MGuildRoulettePrize Management Delete Component', () => {
    let comp: MGuildRoulettePrizeDeleteDialogComponent;
    let fixture: ComponentFixture<MGuildRoulettePrizeDeleteDialogComponent>;
    let service: MGuildRoulettePrizeService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MGuildRoulettePrizeDeleteDialogComponent]
      })
        .overrideTemplate(MGuildRoulettePrizeDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MGuildRoulettePrizeDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MGuildRoulettePrizeService);
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
