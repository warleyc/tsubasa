/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MApRecoveryItemUpdateComponent } from 'app/entities/m-ap-recovery-item/m-ap-recovery-item-update.component';
import { MApRecoveryItemService } from 'app/entities/m-ap-recovery-item/m-ap-recovery-item.service';
import { MApRecoveryItem } from 'app/shared/model/m-ap-recovery-item.model';

describe('Component Tests', () => {
  describe('MApRecoveryItem Management Update Component', () => {
    let comp: MApRecoveryItemUpdateComponent;
    let fixture: ComponentFixture<MApRecoveryItemUpdateComponent>;
    let service: MApRecoveryItemService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MApRecoveryItemUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MApRecoveryItemUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MApRecoveryItemUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MApRecoveryItemService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MApRecoveryItem(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new MApRecoveryItem();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
