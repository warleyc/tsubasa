/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MMarathonBoostItemUpdateComponent } from 'app/entities/m-marathon-boost-item/m-marathon-boost-item-update.component';
import { MMarathonBoostItemService } from 'app/entities/m-marathon-boost-item/m-marathon-boost-item.service';
import { MMarathonBoostItem } from 'app/shared/model/m-marathon-boost-item.model';

describe('Component Tests', () => {
  describe('MMarathonBoostItem Management Update Component', () => {
    let comp: MMarathonBoostItemUpdateComponent;
    let fixture: ComponentFixture<MMarathonBoostItemUpdateComponent>;
    let service: MMarathonBoostItemService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MMarathonBoostItemUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MMarathonBoostItemUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MMarathonBoostItemUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MMarathonBoostItemService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MMarathonBoostItem(123);
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
        const entity = new MMarathonBoostItem();
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
