/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MArousalItemUpdateComponent } from 'app/entities/m-arousal-item/m-arousal-item-update.component';
import { MArousalItemService } from 'app/entities/m-arousal-item/m-arousal-item.service';
import { MArousalItem } from 'app/shared/model/m-arousal-item.model';

describe('Component Tests', () => {
  describe('MArousalItem Management Update Component', () => {
    let comp: MArousalItemUpdateComponent;
    let fixture: ComponentFixture<MArousalItemUpdateComponent>;
    let service: MArousalItemService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MArousalItemUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MArousalItemUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MArousalItemUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MArousalItemService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MArousalItem(123);
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
        const entity = new MArousalItem();
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
