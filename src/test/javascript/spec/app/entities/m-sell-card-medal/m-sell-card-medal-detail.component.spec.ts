/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MSellCardMedalDetailComponent } from 'app/entities/m-sell-card-medal/m-sell-card-medal-detail.component';
import { MSellCardMedal } from 'app/shared/model/m-sell-card-medal.model';

describe('Component Tests', () => {
  describe('MSellCardMedal Management Detail Component', () => {
    let comp: MSellCardMedalDetailComponent;
    let fixture: ComponentFixture<MSellCardMedalDetailComponent>;
    const route = ({ data: of({ mSellCardMedal: new MSellCardMedal(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MSellCardMedalDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MSellCardMedalDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MSellCardMedalDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mSellCardMedal).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
