/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MRecommendShopMessageUpdateComponent } from 'app/entities/m-recommend-shop-message/m-recommend-shop-message-update.component';
import { MRecommendShopMessageService } from 'app/entities/m-recommend-shop-message/m-recommend-shop-message.service';
import { MRecommendShopMessage } from 'app/shared/model/m-recommend-shop-message.model';

describe('Component Tests', () => {
  describe('MRecommendShopMessage Management Update Component', () => {
    let comp: MRecommendShopMessageUpdateComponent;
    let fixture: ComponentFixture<MRecommendShopMessageUpdateComponent>;
    let service: MRecommendShopMessageService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MRecommendShopMessageUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MRecommendShopMessageUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MRecommendShopMessageUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MRecommendShopMessageService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MRecommendShopMessage(123);
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
        const entity = new MRecommendShopMessage();
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
